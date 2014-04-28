package edu.ycp.cs482.webtool.persistence;

import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class FakeDatabase implements IDatabase {

	public FakeDatabase() {
		
	}
	
	@Override
	public void storeWebpage() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User authenticateUser(String inUser, String inPass) {
		// TODO Auto-generated method stub
		return null;
	}

}
