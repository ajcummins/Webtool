package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.model.Page;
import edu.ycp.cs482.webtool.model.User;
import edu.ycp.cs482.webtool.persistence.DatabaseProvider;
import edu.ycp.cs482.webtool.persistence.IDatabase;

public class AddPage {
	public Page addPage(Page page)
	{
		IDatabase db = DatabaseProvider.getInstance();
		return db.addPage(page);
	}
}
