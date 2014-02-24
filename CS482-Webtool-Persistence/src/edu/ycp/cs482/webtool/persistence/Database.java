package edu.ycp.cs482.webtool.persistence;

import edu.ycp.cs482.webtool.persistence.IDatabase;
import edu.ycp.cs482.webtool.persistence.MysqlDatabase;

public class Database {
	private static IDatabase aDatabase = new MysqlDatabase();
	
	public static IDatabase getInstance() {
		return aDatabase;
	}
}
