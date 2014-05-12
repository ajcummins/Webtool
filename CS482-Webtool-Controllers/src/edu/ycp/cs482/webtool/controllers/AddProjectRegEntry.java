package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class AddProjectRegEntry {
	public boolean addProjectRegEntry(int inUserID, int inProjectID)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.addProjectRegEntry(inUserID,inProjectID);
	}
}
