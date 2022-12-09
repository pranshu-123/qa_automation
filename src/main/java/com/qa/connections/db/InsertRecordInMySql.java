package com.qa.connections.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InsertRecordInMySql {

	private static final String INSERT_USERS_SQL = "INSERT INTO features" +
	        "  (EXECUTION_TIME, S_NO, TEST_NAME, TEST_CLASS, FEATURE,TEST_STATUS) VALUES " +
	        " (?, ?, ?, ?, ?,?);";
	
	public void insert(Timestamp timestamp, String build, String testName, String testClass, String feature, String status) {
		String dbURL = "jdbc:postgresql://34.202.230.157:5432/automation_report";
		String username = "postgres";
		String password = "Iris@2022";
		try {
			Connection connection = DriverManager.getConnection(dbURL, username, password);
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
	            preparedStatement.setTimestamp(1, timestamp);
	            preparedStatement.setString(2, build);
	            preparedStatement.setString(3, testName);
	            preparedStatement.setString(4, testClass);
	            preparedStatement.setString(5, feature);
	            preparedStatement.setString(6, status);

	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {

	            // print SQL exception information
	        }
	}

}