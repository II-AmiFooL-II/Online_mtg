package com.mtg_bank.online_mtg.database;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connect {
	private Connection connection = null;

	public Connection getConnection() throws SQLException {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://us-cdbr-east-05.cleardb.net/heroku_64a5a629661950f", "b61142e9b11c9f", "e0fa6b44");
			// DataBase access will be env var'ed and revoked later on, in plain text for testing purposes.
		} catch (ClassNotFoundException e) {

			System.out.println("UNABLE to connect with MySQL JDBC Driver");
			e.printStackTrace();

		}

		return connection;

	}

}
