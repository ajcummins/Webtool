package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class LoginController {
	public User authenticateUser(String inUser, String inPass)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.authenticateUser(inUser,inPass);
	}
}
