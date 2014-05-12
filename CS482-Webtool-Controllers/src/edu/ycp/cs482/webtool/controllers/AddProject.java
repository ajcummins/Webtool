package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class AddProject {
	public Project addProject(Project inProject)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.addProject(inProject);
	}
}
