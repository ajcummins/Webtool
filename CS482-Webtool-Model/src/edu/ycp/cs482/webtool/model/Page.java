package edu.ycp.cs482.webtool.model;

import java.util.ArrayList;

public class Page {
	private int pageID;
	private String pageName;
	private ArrayList<Section> sectionList;
	
	public Page()
	{
		
	}
	
	public Page(String inPageName)
	{
		setPageName(inPageName);
		setSectionList(new ArrayList<Section>());
	}

	public int getPageID() {
		return pageID;
	}

	public void setPageID(int pageID) {
		this.pageID = pageID;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public ArrayList<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(ArrayList<Section> sectionList) {
		this.sectionList = sectionList;
	}
	
	public void addSectionToList(Section inSection)
	{
		sectionList.add(inSection);
	}
	
	public void removeSectionFromList(int inSectionID)
	{
		for(int i = 0 ; i < sectionList.size(); i++)
		{
			if(inSectionID == sectionList.get(i).getSectionID())
			{
				sectionList.remove(i);
			}
		}
	}
}
