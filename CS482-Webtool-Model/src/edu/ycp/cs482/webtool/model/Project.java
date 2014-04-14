package edu.ycp.cs482.webtool.model;

import java.awt.List;
import java.util.ArrayList;


public class Project 
{
	private int projectID;
	private String projectName;
	
	public Project(String inProjectName)
	{
		projectName = inProjectName;
	}
	
	public int getProjectID() {
		return projectID;
	}
	
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
}
