package edu.ycp.cs482.webtool.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs482.webtool.model.Page;
import edu.ycp.cs482.webtool.model.Project;
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
					
					stmt = conn.prepareStatement(
							"create table projects(" +
							"  id integer primary key not null generated always as identity," +
							"  name varchar(20) unique," +
							"  description varchar(100)" +
							")"	
					);
					stmt.executeUpdate();
					
					stmt = conn.prepareStatement(
							"create table projectreg(" +
							"  id integer primary key not null generated always as identity," +
							"  userid integer ," +
							"  projectid integer" +
							")"
					);
					stmt.executeUpdate();
					
					stmt = conn.prepareStatement(
							"create table pages(" +
							"  id integer primary key not null generated always as identity," +
							"  name varchar(20)" +
							")"	
					);
					stmt.executeUpdate();
					
					stmt = conn.prepareStatement(
							"create table pagereg(" +
							"  id integer primary key not null generated always as identity," +
							"  projectid integer ," +
							"  pageid integer" +
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
	
	private void storeProjectNoId(Project inProject, PreparedStatement stmt, int index) throws SQLException{
		stmt.setString(index++, inProject.getProjectName());
		stmt.setString(index++, inProject.getProjectDesc());
	}
	
	private void loadProject(Project project, ResultSet resultSet, int index) throws SQLException {
		project.setProjectID(resultSet.getInt(index++));
		project.setProjectName(resultSet.getString(index++));
		project.setProjectDesc(resultSet.getString(index++));
	}
	
	private void storePageNoId(Page page, PreparedStatement stmt, int index) throws SQLException{
		stmt.setString(index++, page.getPageName());
	}
	
	private void loadPage(Page page, ResultSet resultSet, int index) throws SQLException {
		page.setPageID(resultSet.getInt(index++));
		page.setPageName(resultSet.getString(index++));
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
	public User createUser(final User inUser) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				try{
					stmt = conn.prepareStatement(
							"insert into users (username,password) values (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
							);
					
					storeUserNoId(inUser,stmt,1);
					
					stmt.executeUpdate();
					
					// Get the User ID back
					generatedKeys = stmt.getGeneratedKeys();
					if (!generatedKeys.next()) {
						throw new SQLException("Could not get auto-generated key for inserted Item");
					}
					
					inUser.setUserID(generatedKeys.getInt(1));
					
					return inUser;
				}
				catch(Exception e)
				{
					return null;
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
	
	public List<Project> getAllProjects() {
		return executeTransaction(new Transaction<List<Project>>() {
			@Override
			public List<Project> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// Note: no 'where' so all items will be returned
					stmt = conn.prepareStatement("select projects.* from projects");
					
					resultSet = stmt.executeQuery();
					
					List<Project> result = new ArrayList<Project>();
					while(resultSet.next()){
						Project anotherProject = new Project();
						loadProject(anotherProject, resultSet, 1);
						result.add(anotherProject);
					}
					return result;
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public List<Page> getAllPages() {
		return executeTransaction(new Transaction<List<Page>>() {
			@Override
			public List<Page> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// Note: no 'where' so all items will be returned
					stmt = conn.prepareStatement("select pages.* from pages");
					
					resultSet = stmt.executeQuery();
					
					List<Page> result = new ArrayList<Page>();
					while(resultSet.next()){
						Page anotherPage = new Page();
						loadPage(anotherPage, resultSet, 1);
						result.add(anotherPage);
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
			// Obtain Projects table
			List<Project> projectList = getAllProjects();
			// Obtain Page table
			List<Page> pageList = getAllPages();
			
			// Print out users table
			System.out.println("--------------------------------- Users Table -------------------------------------------");
			for(int i = 0; i < userList.size(); i++)
			{
				System.out.println("UserID: " + userList.get(i).getUserID() + " Username: " + userList.get(i).getUsername() + " Password: " + userList.get(i).getPassword());
			}
			System.out.println("--------------------------------- Projects Table ------------------------------------------");
			for(int i = 0; i< projectList.size(); i++)
			{
				System.out.println("ProjectID: " + projectList.get(i).getProjectID() + " ProjectName: " + projectList.get(i).getProjectName() + " ProjectDesc: " + projectList.get(i).getProjectDesc());
			}
			System.out.println("--------------------------------- Page Table ----------------------------------------------");
			for(int i = 0; i< pageList.size(); i++)
			{
				System.out.println("PageID: " + pageList.get(i).getPageID() + " PageName: " + pageList.get(i).getPageName());
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

	@Override
	public List<Project> getMyProjectList(final User inUser)
	{
		return executeTransaction(new Transaction<List<Project>>() {
			@Override
			public List<Project> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// use user id w/ project reg to get project id's
					stmt = conn.prepareStatement("select projectreg.projectid from projectreg where projectreg.userid = ?");								
					stmt.setInt(1, inUser.getUserID());
					
					resultSet = stmt.executeQuery();
							
					// get a list of Project registry entries
					List<Integer> projectIDs = new ArrayList<Integer>();
					int index = 1;
					while(resultSet.next())
					{
						projectIDs.add(resultSet.getInt(index++));
					}
					// get all the Project w/ the id's
					List<Project> projects = new ArrayList<Project>();
					for(int i = 0; i < projectIDs.size(); i++)
					{
						stmt = conn.prepareStatement("select projects.* from projects where projects.id = ?");
						stmt.setInt(1, projectIDs.get(i));
						
						resultSet = stmt.executeQuery();
						
						// fill the list object with the newly found projects
						resultSet.next();
						Project project = new Project();
						loadProject(project,resultSet,1);
						projects.add(project);
					}
					return projects;
						
				} catch(Exception e){
					return null;
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

		});		
	}


	@Override
	public Project getProjectByName(final String inProjectName) {
		return executeTransaction(new Transaction<Project>() {
			@Override
			public Project execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// get user id w/ username
					stmt = conn.prepareStatement("select projects.* from projects where projects.name = ?");
					stmt.setString(1, inProjectName);
					
					resultSet = stmt.executeQuery();
					if(!resultSet.next()){
						return null;
					}
					else
					{
						Project tempProject = new Project();
						loadProject(tempProject,resultSet,1);
						return tempProject;
					}
					
					
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			});		
	}

	@Override
	public Project addProject(final Project inProject) {
		return executeTransaction(new Transaction<Project>() {
			@Override
			public Project execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				try{
					stmt = conn.prepareStatement(
							"insert into projects (name,description) values (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
					
					storeProjectNoId(inProject,stmt,1);
					
					stmt.executeUpdate();
					
					generatedKeys = stmt.getGeneratedKeys();
					if(!generatedKeys.next()){
						throw new SQLException("Could not get auto-generated key for inserted User");
					}
					
					int projectID = generatedKeys.getInt(1);
					inProject.setProjectID(projectID);
					
					return inProject;
				}
				catch(Exception e){
					e.printStackTrace();
					return null;
				}
				finally{
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		
		});
	}

	@Override
	public boolean addProjectRegEntry(final int inUserID, final int inProjectID) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// Using the project id and user id make project entries
					stmt = conn.prepareStatement(
							"insert into projectreg (userid,projectid) values (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					int index = 1;
					stmt.setInt(index++,inUserID);
					stmt.setInt(index++,inProjectID);
					
					stmt.executeUpdate();

					// Report true if successful
					return true;
					
				} 
				// Report false if unsuccessful
				catch(Exception e){
					return false;
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public List<Page> getProjectPages(final Project inProject) {
		return executeTransaction(new Transaction<List<Page>>() {
			@Override
			public List<Page> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// use project id w/ page reg to get page id's
					stmt = conn.prepareStatement("select pagereg.pageid from pagereg where pagereg.projectid = ?");								
					stmt.setInt(1, inProject.getProjectID());
					
					resultSet = stmt.executeQuery();
							
					// get a list of Project registry entries
					List<Integer> pageIDs = new ArrayList<Integer>();
					int index = 1;
					while(resultSet.next())
					{
						pageIDs.add(resultSet.getInt(index++));
					}
					// get all the pages w/ the id's
					List<Page> pages = new ArrayList<Page>();
					for(int i = 0; i < pageIDs.size(); i++)
					{
						stmt = conn.prepareStatement("select pages.* from pages where pages.id = ?");
						stmt.setInt(1, pageIDs.get(i));
						
						resultSet = stmt.executeQuery();
						
						// fill the list object with the newly found projects
						resultSet.next();
						Page page = new Page();
						loadPage(page,resultSet,1);
						pages.add(page);
					}
					return pages;
						
				} catch(Exception e){
					return null;
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

		});		
	}

	@Override
	public Page addPage(final Page page) {
		return executeTransaction(new Transaction<Page>() {
			@Override
			public Page execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet generatedKeys = null;
				
				try{
					stmt = conn.prepareStatement(
							"insert into pages (name) values (?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
					
					storePageNoId(page,stmt,1);
					
					stmt.executeUpdate();
					
					generatedKeys = stmt.getGeneratedKeys();
					if(!generatedKeys.next()){
						throw new SQLException("Could not get auto-generated key for inserted User");
					}
					
					int pageID = generatedKeys.getInt(1);
					page.setPageID(pageID);
					
					return page;
				}
				catch(Exception e){
					e.printStackTrace();
					return null;
				}
				finally{
					DBUtil.closeQuietly(generatedKeys);
					DBUtil.closeQuietly(stmt);
				}
			}
		
		});
	}

	@Override
	public boolean addPageRegEntry(final int inProjectID,final int inPageID) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// Using the project id and user id make project entries
					stmt = conn.prepareStatement(
							"insert into pagereg (projectid,pageid) values (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS
					);
					
					int index = 1;
					stmt.setInt(index++,inProjectID);
					stmt.setInt(index++,inPageID);
					
					stmt.executeUpdate();

					// Report true if successful
					return true;
					
				} 
				// Report false if unsuccessful
				catch(Exception e){
					return false;
				}
				finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Page getPageByName(final String inPageName, final int inProjectID) {
		return executeTransaction(new Transaction<Page>() {
			@Override
			public Page execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try{
					// get list of pageID's with projectID
					stmt = conn.prepareStatement("select pagereg.pageid from pagereg where pagereg.projectid = ?");								
					stmt.setInt(1, inProjectID);
					
					resultSet = stmt.executeQuery();
							
					// get a list of Project registry entries
					List<Integer> pageIDs = new ArrayList<Integer>();
					int index = 1;
					while(resultSet.next())
					{
						pageIDs.add(resultSet.getInt(index++));
					}
					
					// search through list for matching page name
					Page page = null;
					for(int i = 0; i < pageIDs.size(); i++)
					{
						stmt = conn.prepareStatement("select pages.* from pages where pages.id = ? and pages.name = ?");
						stmt.setInt(1, pageIDs.get(i));
						stmt.setString(2, inPageName);
						
						resultSet = stmt.executeQuery();
						
						// fill the found page;
						if(resultSet.next())
						{
							page = new Page();
							loadPage(page,resultSet,1);
						}
						
					}
					return page;
					
				} catch(Exception e){
					e.printStackTrace();
					return null;
				}finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}

			});		
	}
	
	// Utility methods
	
}
