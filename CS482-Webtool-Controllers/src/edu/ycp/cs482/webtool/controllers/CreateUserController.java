package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class CreateUserController {
	public void createUser(User inUser)
	{
		IDatabase db = DatabaseProvider.getInstance();
		db.createUser(inUser);
	}
}