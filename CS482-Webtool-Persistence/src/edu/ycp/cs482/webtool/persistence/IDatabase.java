package edu.ycp.cs482.webtool.persistence;

import java.sql.Connection;

import edu.ycp.cs482.webtool.model.User;

public interface IDatabase {

	/*
	 * FIXME: Store web page?
	 */
	public void storeWebpage() throws Exception;

	public User authenticateUser(String inUser, String inPass);

	public void createUser(User inUser);
	
}
