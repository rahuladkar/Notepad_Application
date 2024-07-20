package com.alpha.notepad.dto;

public class Notepad {

	private int notepadId;
	private String notepadTitle;
	private String notepadContent;

	// getter() and setter()

	public int getNotepadId() {
		return notepadId;
	}

	public void setNotepadId(int notepadId) {
		this.notepadId = notepadId;
	}

	public String getNotepadTitle() {
		return notepadTitle;
	}

	public void setNotepadTitle(String notepadTitle) {
		this.notepadTitle = notepadTitle;
	}

	public String getNotepadContent() {
		return notepadContent;
	}

	public void setNotepadContent(String notepadContent) {
		this.notepadContent = notepadContent;
	}

	// default constructor
	public Notepad() {

	}

	// constructor to initialize the attribute
	public Notepad(int notepadId, String notepadTitle, String notepadContent) {
		setNotepadId(notepadId);
		setNotepadTitle(notepadTitle);
		setNotepadContent(notepadContent);
	}

	// overridden toString()
	@Override
	public String toString() {
		return "Id = " + notepadId + ", Title = " + notepadTitle + ", Content = "
				+ notepadContent;
	}

}
