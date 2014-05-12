package edu.ycp.cs482.webtool.persistence;

import java.util.List;

import edu.ycp.cs482.webtool.model.Page;
import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.model.User;

public interface IDatabase {

	/*
	 * FIXME: Store web page?
	 */
	public void storeWebpage() throws Exception;

	public User authenticateUser(String inUser, String inPass);

	public User createUser(User inUser);

	public List<Project> getMyProjectList(User inUser);

	public Project getProjectByName(String inProjectName);

	public Project addProject(Project inProject);

	public boolean addProjectRegEntry(int inUserID, int inProjectID);

	public List<Page> getProjectPages(Project inProject);

	public Page addPage(Page page);

	public boolean addPageRegEntry(int inProjectID, int inPageID);

	public Page getPageByName(String inPageName, int inProjectID);
	
}
