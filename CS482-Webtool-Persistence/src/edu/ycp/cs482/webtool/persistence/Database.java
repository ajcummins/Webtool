package edu.ycp.cs482.webtool.persistence;

import edu.ycp.cs482.webtool.persistence.IDatabase;


public class Database {
	private static IDatabase aDatabase = new FakeDatabase();
	
	public static IDatabase getInstance() {
		return aDatabase;
	}
}
