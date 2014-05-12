package edu.ycp.cs482.webtool.model;

import java.awt.List;
import java.util.ArrayList;


public class Project 
{
	private int projectID;
	private String projectName;
	private String projectDesc;
	// Can't represent this in a database with finite limitations... have to use a registry
	//private ArrayList<Integer> pageIDList;
	
	public Project(String inProjectName, String inProjectDescription)
	{
		projectName = inProjectName;
		projectDesc = inProjectDescription;
		//setPageIDList(new ArrayList<Integer>());
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

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	/*
	public ArrayList<Integer> getPageIDList() {
		return pageIDList;
	}

	public void setPageIDList(ArrayList<Integer> pageIDList) {
		this.pageIDList = pageIDList;
	}
	
	public void addPageToList(int inPageID)
	{
		pageIDList.add(inPageID);
	}
	
	public void removePageIDFromList(int inPageID)
	{
		for(int i = 0; i < pageIDList.size(); i++)
		{
			if(pageIDList.get(i) == inPageID)
			{
				pageIDList.remove(i);
			}
		}
	}
	*/
	
	
	
}
