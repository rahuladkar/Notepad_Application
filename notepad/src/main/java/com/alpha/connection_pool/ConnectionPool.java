package com.alpha.connection_pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {

	private static ArrayList<Connection> connections = new ArrayList<Connection>();
	private static String driverPath = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost:5432/eca_jdbc";
	private static String user = "postgres";
	private static String password = "root";
	private static final int POOL_SIZE = 10;

	static {

		try {

			Class.forName(driverPath);

			for (int i = 0; i < POOL_SIZE; i++) {
				Connection connection = createConnection();
				connections.add(connection);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnectionObject() {

		if (!connections.isEmpty()) {
			return connections.remove(0);
		} else {
			return createConnection();
		}

	}

	public static void recieveConnection(Connection connection) {

		if (connections.size() < POOL_SIZE) {
			connections.add(connection);
		} else {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
