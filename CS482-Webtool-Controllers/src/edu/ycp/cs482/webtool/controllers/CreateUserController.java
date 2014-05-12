package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class CreateUserController {
	public User createUser(User inUser)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.createUser(inUser);
	}
}
