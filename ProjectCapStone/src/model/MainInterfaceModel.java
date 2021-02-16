package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import application.Pet;
import common.Log;
import common.MySqlConnection;
import common.ReadConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainInterfaceModel {
	
Connection connection;

public Log log = new Log();
	
	public  MainInterfaceModel() {
	
		ReadConfig readConfig = new ReadConfig();
	
		connection = MySqlConnection.Connector(readConfig.getUrl(), readConfig.getUser(), readConfig.getPassword());
		if (connection == null) {
			log.logFile(null, "severe", "SQL connection is NULL.");
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
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
			return false;
			
		}
		
		finally {
			preparedStatement.close();
			
		}
	}
	
	
	
	public ObservableList<Pet> getPetList(Integer ownerID) throws SQLException {
	  
	    ObservableList<Pet> pet = FXCollections.observableArrayList();

	    PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM pets WHERE OwnerID = ? ";
		
		try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, ownerID);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					
					pet.add(new Pet(resultSet.getString("PetName"), resultSet.getString("PetType") ,resultSet.getString("Breed"), resultSet.getString("Gender"), resultSet.getString("DOB")));
				}
				
			
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
		finally {
			preparedStatement.close();
			resultSet.close();
		}

	    return pet;
	  }
	
	public Integer getOwnerID(String icNumber) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT OwnerID FROM owners WHERE ICNumber = ?";
		Integer id = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, icNumber);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				 id = resultSet.getInt("OwnerID");
				
			}
			
			return id;
			
		
	} catch (Exception e) {
		log.logFile(e, "severe", e.getMessage());
		e.printStackTrace();
		return null;
	}
	
	finally {
		preparedStatement.close();
		resultSet.close();
	}
		
		
	}
	
	public ObservableList<Pet> getPetInfo(Integer OwnerID, String petName, String petType, String dob) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM pets WHERE (OwnerID = ? AND PetName = ? AND PetType = ? AND DOB = ?)";
		//Map<String, Object> petInfo = new HashMap<>();
		  ObservableList<Pet> petInfo = FXCollections.observableArrayList();
		
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, OwnerID);
			preparedStatement.setString(2, petName);
			preparedStatement.setString(3, petType);
			preparedStatement.setString(4, dob);
			resultSet = preparedStatement.executeQuery();
			
			
			
			
			while (resultSet.next()) {
				/*
				petInfo.put("PetID", resultSet.getInt("PetID"));
				petInfo.put("PetName", resultSet.getString("PetName"));
				petInfo.put("PetType", resultSet.getString("PetType"));
				petInfo.put("Breed", resultSet.getString("Breed"));
				petInfo.put("Gender", resultSet.getString("Gender"));
				petInfo.put("DOB", resultSet.getString("DOB"));
				*/
				petInfo.add(new Pet(resultSet.getString("PetName"), resultSet.getString("PetType") ,resultSet.getString("Breed"), resultSet.getString("Gender"), resultSet.getString("DOB")));
				
			}
			
			
			
		
			
		
	} catch (Exception e) {
		log.logFile(e, "severe", e.getMessage());
		e.printStackTrace();
		
	}
	
	finally {
		preparedStatement.close();
		resultSet.close();
	}
		
		return petInfo;
		
		
	}
	

}
