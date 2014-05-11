package edu.ycp.cs482.webtool.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs482.webtool.model.User;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

// Database relative methods
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}

	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/AJC/git/482-Webtool-Repository/CS482-Webtool-Persistence/test.db;create=true"); //Josh
		//Connection conn = DriverManager.getConnection("jdbc:derby:PATH HERE/test.db;create=true"); //Anthony 
		
		// Set autocommit to false to allow multiple the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					// Note that the 'id' column is an autoincrement primary key,
					// so SQLite will automatically assign an id when rows
					// are inserted.
					
					stmt = conn.prepareStatement(
							"create table users (" +
							"  id integer primary key not null generated always as identity," +
							"  username varchar(20) unique," +
							"  password varchar(20)" +
							")"
					);
					
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
				
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					/* If we want to set the db to an initial state */
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public static void main(String[] args) {
		DerbyDatabase db = new DerbyDatabase();
		System.out.println("Creating tables...");
		db.createTables();
		//System.out.println("Loading initial data...");
		//db.loadInitialData();
		System.out.println("Done!");
	}

// Utility methods to make database interactions easier
	protected void storeUserNoId(User inUser, PreparedStatement stmt, int index) throws SQLException{
		/*
		 * A unique id should be automatically generated...
		 */
		stmt.setString(index++, inUser.getUsername());
		stmt.setString(index++, inUser.getPassword());
	}
	
	
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		//FIXME: Do something with the user id from the table
		user.setUserID(resultSet.getInt(index++));	
		user.setUsername(resultSet.getString(index++));
		user.setPassword(resultSet.getString(index++));
	}
	
	
// Controller Methods	

	@Override
	public User authenticateUser(final String user,final String pass) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					stmt = conn.prepareStatement("select users.* from users where users.username = ? AND users.password = ?");
					stmt.setString(1, user);
					stmt.setString(2, pass);	
					
					resultSet = stmt.executeQuery();
					
					if(!resultSet.next()){
						// invalid User parameters, User could not be found
						return null;
					}
					
					User user = new User();
					loadUser(user, resultSet,1);
					printTables();
					return user;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

		});
	}

	@Override
	public Boolean createUser(final User inUser) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				try{
					stmt = conn.prepareStatement(
							"insert into users (username,password) values (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
					
					storeUserNoId(inUser,stmt,1);
					
					stmt.executeUpdate();

					return true;
				}
				catch(Exception e)
				{
					return false;
				}
				finally
				{
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		
		});
	}
	
	public List<User> getAllUsers() {
		// Return the entire Users table 
		return executeTransaction(new Transaction<List<User>>() {
			@Override
			public List<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// Note: no 'where' so all items will be returned
					stmt = conn.prepareStatement("select users.* from users");
					
					resultSet = stmt.executeQuery();
					
					List<User> result = new ArrayList<User>();
					while(resultSet.next()){
						User anotherUser = new User();
						loadUser(anotherUser, resultSet, 1);
						result.add(anotherUser);
					}
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

		});		
		
	}
	
	public void printTables() throws SQLException {
		try {
			// Obtain Users table
			List<User> userList = getAllUsers();
			// Print out users table
			System.out.println("--------------------------------- Users Table -------------------------------------------");
			for(int i = 0; i < userList.size(); i++)
			{
				System.out.println("UserID: " + userList.get(i).getUserID() + " Username: " + userList.get(i).getUsername() + " Password: " + userList.get(i).getPassword());
			}
			
		} 
		catch(Exception e){
			System.out.println("Print Tables ERROR ");
			e.printStackTrace();
		}
	}

	@Override
	public void storeWebpage() throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
	// Utility methods
	
}
