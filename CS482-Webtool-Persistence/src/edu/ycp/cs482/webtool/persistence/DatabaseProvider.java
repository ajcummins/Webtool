package edu.ycp.cs482.webtool.persistence;

public class DatabaseProvider {
	private static IDatabase theInstance;
	
	public static void setInstance(IDatabase db)
	{
		theInstance = db;
	}
	
	public static IDatabase getInstance()
	{
		return theInstance;
	}
}
