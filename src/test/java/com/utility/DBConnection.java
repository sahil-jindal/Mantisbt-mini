package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	Connection conn;

	public Connection getConnection() {
		if (this.conn == null) {
			this.conn = createConnection();
		}
		
		return this.conn;
	}

	private Connection createConnection() {

		String conn_url = "jdbc:mysql://localhost:3306/bugtracker";
		String UN = "root";
		String PWD = "";
		this.conn = null;
		try {
			this.conn = DriverManager.getConnection(conn_url, UN, PWD);
			return this.conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return this.conn;
	}
}
