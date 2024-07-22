package com.alpha.notepad.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.alpha.notepad.dto.Notepad;

class NotepadDaoTest {

	@Test
	void testSaveNotepad() {

		Notepad notepad = new Notepad();
		notepad.setNotepadId(4210);
		notepad.setNotepadTitle("Java");
		notepad.setNotepadContent("This is Java.");

		NotepadDao dao = new NotepadDao();

		assertEquals(notepad, dao.saveNotepad(notepad));

	}

	@Test
	void testUpdateNotepad() {
		Notepad notepad = new Notepad();
		notepad.setNotepadId(4210);
		notepad.setNotepadTitle("Java");
		notepad.setNotepadContent("This is Java.");

		NotepadDao dao = new NotepadDao();

		assertTrue(dao.updateNotepad(notepad).equals(notepad));
	}

	@Test
	void testFindNotepadById() {
		Notepad notepad = new Notepad();
		notepad.setNotepadId(4210);

		NotepadDao dao = new NotepadDao();

		assertTrue(dao.findNotepadById(notepad.getNotepadId()) != null);
	}

	@Test
	void testFindAll() {
		Notepad notepad = new Notepad();

		NotepadDao dao = new NotepadDao();

		assertAll(() -> dao.findAll());
	}

	@Test
	void testDeleteById() {
		Notepad notepad = new Notepad();
		notepad.setNotepadId(4210);

		NotepadDao dao = new NotepadDao();

		assertTrue(dao.deleteById(notepad.getNotepadId()));
	}

}
