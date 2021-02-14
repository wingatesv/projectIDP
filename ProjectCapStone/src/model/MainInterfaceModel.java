package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.MySqlConnection;
import common.ReadConfig;

public class MainInterfaceModel {
	
Connection connection;
	
	public  MainInterfaceModel() {
	
		ReadConfig readConfig = new ReadConfig();
	
		connection = MySqlConnection.Connector(readConfig.getUrl(), readConfig.getUser(), readConfig.getPassword());
		if (connection == null) {
			System.exit(1);
			
		}
	}
	
	public boolean addOwnerInfo(String firstName, String lastName, String pNumber, String icNumber, String address) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		
		
		String query = "INSERT into owners (FirstName, LastName, PhoneNumber, ICNumber, Address) values (?, ?, ?, ?, ?)";
		
		try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, pNumber);
				preparedStatement.setString(4, icNumber);
				preparedStatement.setString(5, address);
				
				
			if (preparedStatement.executeUpdate() == 1) {		
			
				return true;
				
				
			}
			else {
				
				return false;
			}
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		finally {
			preparedStatement.close();
			
		}
	}
		

}
