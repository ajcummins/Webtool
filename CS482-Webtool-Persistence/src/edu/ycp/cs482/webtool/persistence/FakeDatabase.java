package edu.ycp.cs482.webtool.persistence;

import java.util.ArrayList;

import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class FakeDatabase /*implements IDatabase*/ {

	private ArrayList<User> allUsers;
	
	public FakeDatabase() {
		// Add Users to fake allUsers list
		 allUsers = new ArrayList<User>();
		
		User ao = new User("ao","test");
		User ajcummins = new User("ajcummins","test");
		
		allUsers.add(ao);
		allUsers.add(ajcummins);
	}
	
//	@Override
	public void storeWebpage() throws Exception {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public User authenticateUser(String inUser, String inPass) {
		for(int i = 0; i < allUsers.size(); i++)
		{
			// if username's match
			if(allUsers.get(i).getUsername().equals(inUser))
			{
				// if password's match
				if(allUsers.get(i).getPassword().equals(inPass))
				{
					return allUsers.get(i);
				}
			}
		}
		return null;
	}

//	@Override
	public void createUser(User inUser) {
		allUsers.add(inUser);		
	}

}
