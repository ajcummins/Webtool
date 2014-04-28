package edu.ycp.cs482.webtool.model;

public class Division {
	private DivisionType divisionType;
	private Object divisionObject;
	
	public Division(DivisionType inType, Object inObject)
	{
		divisionType = inType;
		divisionObject = inObject;
	}
	
	public DivisionType getDivisionType() {
		return divisionType;
	}
	public void setDivisionType(DivisionType divisionType) {
		this.divisionType = divisionType;
	}
	public Object getDivisionObject() {
		return divisionObject;
	}
	public void setDivisionObject(Object divisionObject) {
		this.divisionObject = divisionObject;
	}
	
}
