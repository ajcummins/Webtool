package edu.ycp.cs482.webtool.model;

import java.awt.List;
import java.util.ArrayList;


public class User 
{
	private String username;
	private String password;
	private ArrayList<Integer> projectIDList;
	
	public User(String inUsername, String inPassword)
	{
		username = inUsername;
		password = inPassword;
		projectIDList = new ArrayList<Integer>();
	}
	
	public void setUsername(String inUsername)
	{
		username = inUsername;
		
	}
	
	public void setPassword(String inPassword)
	{
		password = inPassword;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public ArrayList<Integer> getProjectNameList()
	{
		return projectIDList;
	}
	
	
}
