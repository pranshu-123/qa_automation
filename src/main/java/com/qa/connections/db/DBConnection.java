package com.qa.connections.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	Connection con ;

	public Statement makeDBConnection() {

		String jdbc_driver = "org.postgresql.Driver";
		String dbURL = "jdbc:postgresql://34.202.230.157:5432/automation_report";
		String username = "postgres";
		String password = "Iris@2022";
		Statement stmt = null;
		try {
			//Load MySQL JDBC Driver
			Class.forName(jdbc_driver);

			//Establish connection with the database
			con = DriverManager.getConnection(dbURL,username,password);

			//Create Statement object
			stmt = con.createStatement();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

