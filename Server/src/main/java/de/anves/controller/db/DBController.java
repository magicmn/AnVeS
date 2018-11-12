package de.anves.controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

	private static DBController instance;
	
	/**
	 * Singolton Pattern
	 * @return
	 */
	public static DBController getInstance() {
		if(instance == null) {
			instance = new DBController();
		}
		return instance;
	}
	
	private DBController() {}
	
	//--------------------------------------------------------------
	
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String USER = "root";
	private static final String PASSWORT = "";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private Connection connection;
	
	public boolean connect() {
		try {
			Class.forName(DRIVER).newInstance();
			connection = DriverManager.getConnection(URL, USER, PASSWORT);
			return !connection.isClosed();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("Treiber nicht gefunden.");
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			System.err.println("Verbindung zur Datenbank konnte nicht hergestellt werden.");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isConnected() throws SQLException {
		if(connection != null) {
			return !connection.isClosed();
		}
		return false;
	}
	
	public void closeConnection() throws SQLException {
		if(connection != null) {
			connection.close();
		}
	}
	
	public int executeUpdate(String sql) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(sql);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(sql);
	}
	
}
