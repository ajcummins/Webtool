package edu.ycp.cs482.webtool.controllers;


import edu.ycp.cs482.webtool.model.Page;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class GetPageByName {
	public Page getPageByName(String inPageName, int inProjectID)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.getPageByName(inPageName,inProjectID);
	}
}
