package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.Log;
import common.MySqlConnection;
import common.ReadConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddVacRecordModel {
	
	Connection connection;
	public Log log = new Log();
		
		public AddVacRecordModel() {
		
			ReadConfig readConfig = new ReadConfig();
		
			connection = MySqlConnection.Connector(readConfig.getUrl(), readConfig.getUser(), readConfig.getPassword());
			if (connection == null) {
				log.logFile(null, "severe", "SQL connection is NULL.");
				System.exit(1);
				
			}
		}
		
	public boolean addVacRecord(Integer petID, String todayDate, String nextDate ,String injection, String vaccine) throws SQLException {
			
			PreparedStatement preparedStatement = null;
			String query = "INSERT into vaccine_records (PetID, Date, NextDate, Injection, Vaccine) values (?, ?, ?, ?, ?)";
			
			try {
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(1, petID);
					preparedStatement.setString(2, todayDate);
					preparedStatement.setString(3, nextDate);
					preparedStatement.setString(4, injection);
					preparedStatement.setString(5, vaccine);
					
					
					
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
	
	
public ObservableList<String> getVaccineDog() throws SQLException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM vaccine_dog";
		  ObservableList<String> vaccineDogs = FXCollections.observableArrayList();
		
		
		try {
				preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				
				
				while (resultSet.next()) {
					
					vaccineDogs.add(resultSet.getString("VaccineName"));
					
				}		
		
	} catch (Exception e) {
		log.logFile(e, "severe", e.getMessage());
		e.printStackTrace();
		
	}
	
	finally {
		preparedStatement.close();
		resultSet.close();
	}
		
		return vaccineDogs;
		
		
	}

public ObservableList<String> getVaccineCat() throws SQLException {
	
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String query = "SELECT * FROM vaccine_cat";
	  ObservableList<String> vaccineCats = FXCollections.observableArrayList();
	
	
	try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				vaccineCats.add(resultSet.getString("VaccineName"));
				
			}
	
	
	} catch (Exception e) {
		log.logFile(e, "severe", e.getMessage());
		e.printStackTrace();
		
	}
	
	finally {
		preparedStatement.close();
		resultSet.close();
	}
		
		return vaccineCats;
		
		
	}
			
			
		

}
