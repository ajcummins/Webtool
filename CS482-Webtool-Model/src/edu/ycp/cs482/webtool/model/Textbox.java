package edu.ycp.cs482.webtool.model;

public class Textbox {
	/*
	 * Class for Storing webtext variables
	 * 
	 */
	private Alignment align;
	private boolean bold;
	private boolean italicized;
	private boolean underlined;
	private int size;
	private Font font;
	private String text;
	
	public Textbox(Alignment inAlign,int inSize,Font inFont, String inText)
	{
		setAlign(inAlign);
		setSize(inSize);
		setFont(inFont);
		setText(inText);
		setBold(false);
		setItalicized(false);
		setUnderlined(false);
	}

	public Alignment getAlign() {
		return align;
	}

	public void setAlign(Alignment align) {
		this.align = align;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isItalicized() {
		return italicized;
	}

	public void setItalicized(boolean italicized) {
		this.italicized = italicized;
	}

	public boolean isUnderlined() {
		return underlined;
	}

	public void setUnderlined(boolean underlined) {
		this.underlined = underlined;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
