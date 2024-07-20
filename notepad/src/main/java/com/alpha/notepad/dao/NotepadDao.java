package com.alpha.notepad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.alpha.notepad.dto.Notepad;

public class NotepadDao {

	final private static String url = "jdbc:postgresql://localhost:5432/eca_jdbc";
	final private static String user = "postgres";
	final private static String password = "root";
	static Connection conn = null;

	static {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public Notepad saveNotepad(Notepad np) {
		
		try {
			PreparedStatement pstm = conn.prepareStatement("insert into notepad values(?,?,?)");
			pstm.setInt(1, np.getNotepadId());
			pstm.setString(2, np.getNotepadTitle());
			pstm.setString(3, np.getNotepadContent());

			if(pstm.execute()==false) {
				return np;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Notepad updateNotepad(Notepad np) {

		try {

			if (np.getNotepadTitle() != null) {
				PreparedStatement pstm = conn
						.prepareStatement("update notepad set notepad_title=? where notepad_id = ?");
				pstm.setString(1, np.getNotepadTitle());
				pstm.setInt(2, np.getNotepadId());
				pstm.execute();

			} else if (np.getNotepadContent() != null) {
				PreparedStatement pstm = conn
						.prepareStatement("update notepad set notepad_content=? where notepad_id = ?");
				pstm.setString(1, np.getNotepadContent());
				pstm.setInt(2, np.getNotepadId());
				pstm.execute();

			}

			return findNotepadById(np.getNotepadId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public Notepad findNotepadById(int id) {

		try {
			PreparedStatement pstm = conn.prepareStatement("select * from notepad where notepad_id=?");
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				return new Notepad(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Notepad> findAll() {

		ArrayList<Notepad> data = new ArrayList<Notepad>();

		try {
			PreparedStatement pstm = conn.prepareStatement("select * from notepad");

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				data.add(new Notepad(rs.getInt(1), rs.getString(2), rs.getString(3)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return data;
	}

	public boolean deleteById(int id) {

		try {
			PreparedStatement pstm = conn.prepareStatement("delete from notepad where notepad_id=?");

			pstm.setInt(1, id);

			if (pstm.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}