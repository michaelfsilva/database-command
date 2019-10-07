package br.com.databasecommand.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conn Class: create a connection on a database based on passed information.
 * 
 * @author michael_silva
 *
 */

public class ConnectionFactory {
	
	/**
	 * Generate the connection url and call driver manager to create the connection.
	 * 
	 * @param server
	 * @param database
	 * @return connection
	 */
	public Connection getConnection(String server, String database, String username, String password) {
		try {
			String url = "jdbc:postgresql://" + server + ":5432/" + database;
			
			return DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}