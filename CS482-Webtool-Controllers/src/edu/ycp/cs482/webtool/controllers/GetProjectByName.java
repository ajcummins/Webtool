package edu.ycp.cs482.webtool.controllers;

import java.util.List;

import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class GetProjectByName {
	public Project getProjectByName(String inProjectName)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.getProjectByName(inProjectName);
	}
}
