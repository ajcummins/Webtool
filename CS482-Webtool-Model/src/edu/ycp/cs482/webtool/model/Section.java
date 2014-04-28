package edu.ycp.cs482.webtool.model;

import java.util.ArrayList;

public class Section {
	// section id's increment, the first (top most) section is 0, increasing from there
	private int sectionID;
	private ArrayList<Division> divisionList;
	
	public Section(int inSectionID)
	{
		sectionID = inSectionID;
		divisionList = new ArrayList<Division>();
	}
	
	public int getSectionID() {
		return sectionID;
	}
	
	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}
	
	public ArrayList<Division> getDivisionList() {
		return divisionList;
	}
	
	public void setDivisionList(ArrayList<Division> divisionList) {
		this.divisionList = divisionList;
	}
}
