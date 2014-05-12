package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class AddPageRegEntry {
	public boolean addPageRegEntry(int inProjectID, int inPageID)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.addPageRegEntry(inProjectID,inPageID);
	}
}
