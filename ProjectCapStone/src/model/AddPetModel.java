package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.Log;
import common.MySqlConnection;
import common.ReadConfig;

public class AddPetModel {
	
Connection connection;
public Log log = new Log();
	
	public AddPetModel() {
	
		ReadConfig readConfig = new ReadConfig();
	
		connection = MySqlConnection.Connector(readConfig.getUrl(), readConfig.getUser(), readConfig.getPassword());
		if (connection == null) {
			log.logFile(null, "severe", "SQL connection is NULL.");
			System.exit(1);
			
		}
	}
	
public boolean addPetInfo(Integer ownerID, String petName, String petType ,String breed, String gender, String dob, String neutered) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		String query = "INSERT into pets (OwnerID, PetName, PetType, Breed, Gender, DOB, Neutered) values (?, ?, ?, ?, ?, ?, ?)";
		
		try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, ownerID);
				preparedStatement.setString(2, petName);
				preparedStatement.setString(3, petType);
				preparedStatement.setString(4, breed);
				preparedStatement.setString(5, gender);
				preparedStatement.setString(6, dob);
				preparedStatement.setString(7, neutered);
				
				
			if (preparedStatement.executeUpdate() == 1) {		
			
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
			
		}
	}
	
	
	

}
