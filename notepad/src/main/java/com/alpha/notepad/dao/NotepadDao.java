package com.alpha.notepad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.alpha.connection_pool.ConnectionPool;
import com.alpha.notepad.dto.Notepad;

public class NotepadDao {

	public Notepad saveNotepad(Notepad np) {

		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionObject();
			PreparedStatement pstm = conn.prepareStatement("insert into notepad values(?,?,?)");
			pstm.setInt(1, np.getNotepadId());
			pstm.setString(2, np.getNotepadTitle());
			pstm.setString(3, np.getNotepadContent());

			if (pstm.execute() == false) {
				return np;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (conn != null) {
				ConnectionPool.recieveConnection(conn);
			}
		}

		return null;
	}

	public Notepad updateNotepad(Notepad np) {

		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionObject();

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

			} else {
				PreparedStatement pstm = conn.prepareStatement(
						"update notepad set notepad_title=? , notepad_content=? where notepad_id = ?");
				pstm.setString(1, np.getNotepadTitle());
				pstm.setString(2, np.getNotepadContent());
				pstm.setInt(3, np.getNotepadId());
				pstm.execute();
			}

			return findNotepadById(np.getNotepadId());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (conn != null) {
				ConnectionPool.recieveConnection(conn);
			}
		}

		return null;

	}

	public Notepad findNotepadById(int id) {

		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionObject();

			PreparedStatement pstm = conn.prepareStatement("select * from notepad where notepad_id=?");
			pstm.setInt(1, id);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				return new Notepad(rs.getInt(1), rs.getString(2), rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (conn != null) {
				ConnectionPool.recieveConnection(conn);
			}
		}

		return null;
	}

	public List<Notepad> findAll() {

		ArrayList<Notepad> data = new ArrayList<Notepad>();

		Connection conn = null;

		try {
			conn = ConnectionPool.getConnectionObject();

			PreparedStatement pstm = conn.prepareStatement("select * from notepad order by notepad_id");

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				data.add(new Notepad(rs.getInt(1), rs.getString(2), rs.getString(3)));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (conn != null) {
				ConnectionPool.recieveConnection(conn);
			}
		}

		return data;
	}

	public boolean deleteById(int id) {

		Connection conn = null;
		try {
			conn = ConnectionPool.getConnectionObject();

			PreparedStatement pstm = conn.prepareStatement("delete from notepad where notepad_id=?");

			pstm.setInt(1, id);

			if (pstm.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (conn != null) {
				ConnectionPool.recieveConnection(conn);
			}
		}

		return false;
	}

}