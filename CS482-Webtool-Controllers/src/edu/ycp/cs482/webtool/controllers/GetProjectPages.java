package edu.ycp.cs482.webtool.controllers;

import java.util.List;

import edu.ycp.cs482.webtool.model.Page;
import edu.ycp.cs482.webtool.model.Project;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class GetProjectPages {
	public List<Page> getProjectPages(Project inProject)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.getProjectPages(inProject);
	}
}
