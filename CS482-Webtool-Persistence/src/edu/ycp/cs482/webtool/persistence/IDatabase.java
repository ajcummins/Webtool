package edu.ycp.cs482.webtool.persistence;

import java.sql.Connection;
import java.util.List;

import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.model.User;

public interface IDatabase {

	/*
	 * FIXME: Store web page?
	 */
	public void storeWebpage() throws Exception;

	public User authenticateUser(String inUser, String inPass);

	public Boolean createUser(User inUser);

	public List<Project> getMyProjectList(User inUser);

	public Project getProjectByName(String inProjectName);
	
}
