package edu.ycp.cs482.webtool.controllers;

import edu.ycp.cs482.webtool.persistence.Database;


public class webtoolController {
	//FIXME: storing webpages...
	public void storeWebpage() throws Exception{
		Database.getInstance().storeWebpage();
	}
}
