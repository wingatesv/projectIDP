package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Log;
import common.MySqlConnection;
import common.Owner;
import common.Pet;
import common.ReadConfig;
import common.VaccineRecord;
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
	
	public boolean addOwnerInfo(String firstName, String lastName, String icNumber, String pNumber, String address) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		String query = "INSERT into owners (FirstName, LastName, IcNumber, PhoneNumber, Address) values (?, ?, ?, ?, ?)";
		
		try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, icNumber);
				preparedStatement.setString(4, pNumber);
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
	
	public ObservableList<Owner> searchOwner(String firstName, String lastName) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM owners WHERE (FirstName = ? AND LastName = ?)";
		ObservableList<Owner> owners = FXCollections.observableArrayList();
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				owners.add(new Owner(resultSet.getString("FirstName"), resultSet.getString("LastName"), resultSet.getString("IcNumber"), resultSet.getString("PhoneNumber"), resultSet.getString("Address")));
			}
			
		
	} catch (Exception e) {
		log.logFile(e, "severe", e.getMessage());
		e.printStackTrace();
	}
	
	finally {
		preparedStatement.close();
		resultSet.close();
	}
		 
		 
		 
		 
		 
		return owners;
	}
	
	
	public boolean editOwnerInfo(String firstName, String lastName, String pNumber, String address, Integer ownerID) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		String query = "UPDATE owners set FirstName = ?, LastName = ?, PhoneNumber = ?, Address = ? WHERE OwnerID = ?";
		
		try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, firstName);
				preparedStatement.setString(2, lastName);
				preparedStatement.setString(3, pNumber);
				preparedStatement.setString(4, address);
				preparedStatement.setInt(5, ownerID);
				
				
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
					
					pet.add(new Pet(resultSet.getString("PetName"), resultSet.getString("PetType") ,
							resultSet.getString("Breed"), resultSet.getString("Gender"), resultSet.getString("DOB"), resultSet.getString("Neutered")));
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
	
	public Integer getPetID(Integer ownerID, String petName, String petType) throws SQLException{
			
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String query = "SELECT PetID FROM pets WHERE (OwnerID = ? AND PetName = ? AND PetType = ?)";
			Integer id = null;
			
			try {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1, ownerID);
				preparedStatement.setString(2, petName);
				preparedStatement.setString(3, petType);
				resultSet = preparedStatement.executeQuery();
				
				while (resultSet.next()) {
					
					 id = resultSet.getInt("PetID");
					
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
	
	public ObservableList<Pet> getPetInfo(Integer PetID) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM pets WHERE PetID = ?";
		 ObservableList<Pet> petInfo = FXCollections.observableArrayList();
		
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, PetID);
			resultSet = preparedStatement.executeQuery();
			
			
			
			
			while (resultSet.next()) {
				
				petInfo.add(new Pet(resultSet.getString("PetName"), resultSet.getString("PetType") 
						,resultSet.getString("Breed"), resultSet.getString("Gender"), resultSet.getString("DOB"), resultSet.getString("Neutered")));
				
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
	
	public ObservableList<VaccineRecord> getVaccineRecord(Integer petID) throws SQLException {
		  ObservableList<VaccineRecord> vaccineRecords = FXCollections.observableArrayList();

		    PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			String query = "SELECT * FROM vaccine_records WHERE PetID = ? ";
			
			try {
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, petID);
					resultSet = preparedStatement.executeQuery();
					
					while (resultSet.next()) {
						
						vaccineRecords.add(new VaccineRecord(String.valueOf(resultSet.getInt("VacID")), resultSet.getString("Vaccine") ,resultSet.getString("Injection"), resultSet.getString("Date"), resultSet.getString("NextDate")));
					}
					
				
			} catch (Exception e) {
				log.logFile(e, "severe", e.getMessage());
				e.printStackTrace();
			}
			
			finally {
				preparedStatement.close();
				resultSet.close();
			}

		    return vaccineRecords;
	}
	

}
