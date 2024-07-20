package com.alpha.notepad.controller;

import java.util.Scanner;

import com.alpha.notepad.dao.NotepadDao;
import com.alpha.notepad.dto.Notepad;

public class NotepadController {

	static Scanner ip = new Scanner(System.in);
	static NotepadDao nd = new NotepadDao();
	static NotepadController nc = new NotepadController();

	public void saveNote() {

		System.out.print("Enter the Notepad Id : ");
		int nid = ip.nextInt();
		ip.nextLine();

		System.out.print("Enter the Notepad Title : ");
		String title = ip.nextLine();

		System.out.print("Enter the Notepad Content : ");
		String content = ip.nextLine();

		if (nd.saveNotepad(new Notepad(nid, title, content)) != null) {
			System.out.println("\nSaved Successfully...!");
		} else {
			System.out.println("\nSomethig went wrong...!");
		}

	}

	public void updateNote() {

		System.out.println("\n1. Update Title");
		System.out.println("2. Update Content");
		System.out.print("\nselect option : ");
		int choice = ip.nextInt();
		ip.nextLine();

		switch (choice) {
		case 1: {
			System.out.print("\nEnter The Notepad ID : ");
			int id = ip.nextInt();
			ip.nextLine();
			System.out.print("Enter The new Notepad Title : ");
			String title = ip.nextLine();
			System.out.println("\n" + nd.updateNotepad(new Notepad(id, title, null)));
		}
			break;

		case 2: {
			System.out.print("\nEnter The Notepad ID : ");
			int id = ip.nextInt();
			ip.nextLine();
			System.out.print("Enter The Notepad Content : ");
			String content = ip.nextLine();
			System.out.println("\n" + nd.updateNotepad(new Notepad(id, null, content)));
		}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value");
		}

	}

	public void findById() {

		System.out.print("\nEnter the Notepad ID : ");
		int id = ip.nextInt();

		if (nd.findNotepadById(id) != null) {
			System.out.println("\n" + nd.findNotepadById(id));
		}else{
			System.out.println("\nRecord Not Found...!");
		}

	}

	public void findAll() {

		System.out.println("\n--------------------------- All NotePad Records ---------------------------\n");
		nd.findAll().forEach(System.out::println);

	}

	public void deleteById() {

		System.out.print("\nEnter the Notepad ID : ");
		int id = ip.nextInt();

		if (nd.deleteById(id)) {
			System.out.println("\nSuccessfully Deleted...!");
		}
	}

	public static void main(String... args) {

		System.out.println("\n--------------------------- Notepad Application ---------------------------");

		while (true) {

			System.out.println("\n1. Save the Note");
			System.out.println("2. Update the Note");
			System.out.println("3. Find Note By ID");
			System.out.println("4. Show All Notes");
			System.out.println("5. Delete Note By ID");
			System.out.println("0. Exit");
			System.out.print("\nSelect option : ");
			int choice = ip.nextInt();
			ip.nextLine();

			switch (choice) {
			case 1:
				nc.saveNote();
				break;
			case 2:
				nc.updateNote();
				break;
			case 3:
				nc.findById();
				break;
			case 4:
				nc.findAll();
				break;
			case 5:
				nc.deleteById();
				break;
			case 0:
				System.out.println("\nBye.....!");
				System.exit(1);

			default:
				throw new IllegalArgumentException("\nUnexpected value: " + choice);
			}

		}

	}

}
