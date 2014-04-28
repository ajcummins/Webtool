package edu.ycp.cs482.webtool.model;

public class Picture {
	private String src;
	private int width;
	private int height;
	private Alignment align;
	
	public Picture(String inSrc, int inWidth, int inHeight, Alignment inAlign)
	{
		setSrc(inSrc);
		setWidth(inWidth);
		setHeight(inHeight);
		setAlign(inAlign);
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Alignment getAlign() {
		return align;
	}

	public void setAlign(Alignment align) {
		this.align = align;
	}
	
}
