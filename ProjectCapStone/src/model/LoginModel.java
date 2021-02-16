package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Log;
import common.MySqlConnection;
import common.ReadConfig;

public class LoginModel {
	
	Connection connection;
	public Log log = new Log();
	
	public LoginModel() {	
		
		ReadConfig readConfig = new ReadConfig();
	
		connection = MySqlConnection.Connector(readConfig.getUrl(), readConfig.getUser(), readConfig.getPassword());
		if (connection == null) {
			log.logFile(null, "severe", "SQL connection is NULL.");
			System.exit(1);
			
		}
	}
	
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean isLogin(String user, String pass) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM user where username = ? and password = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return true;
				
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		finally {
			preparedStatement.close();
			resultSet.close();
		}
	}

}

