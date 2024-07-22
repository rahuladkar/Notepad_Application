package com.alpha.notepad.dto;

import java.util.Objects;

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
		return "Id = " + notepadId + "  ,  Title = " + notepadTitle + "  ,  Content = "
				+ notepadContent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(notepadContent, notepadId, notepadTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notepad other = (Notepad) obj;
		return Objects.equals(notepadContent, other.notepadContent) && notepadId == other.notepadId
				&& Objects.equals(notepadTitle, other.notepadTitle);
	}
	

}
