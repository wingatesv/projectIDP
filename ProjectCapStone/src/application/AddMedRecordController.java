package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import common.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.scene.control.TextField;
import model.AddMedRecordModel;
import javafx.scene.control.Alert.AlertType;

public class AddMedRecordController implements Initializable {

	@FXML private DatePicker datePicker_MedDate;
	@FXML private JFXTimePicker timePicker_Time;
	@FXML private TextField textField_Medication;
	@FXML private TextField textField_Dosage;
	@FXML private TextField textField_Frequency;
	@FXML private TextField textField_Notes;
	
	private Integer petID;
	private String petName;
	private String ownerName;
	private Integer ownerID;
	
	public AddMedRecordModel model = new AddMedRecordModel();
	public Log log = new Log();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker_MedDate.setValue(LocalDate.now());
	}
	
	public void setPetID(Integer petID) {
		this.petID = petID;
		
	}
	
	public void setOwnerID(Integer ownerID) {
		this.ownerID = ownerID;
		
	}
	
	public void setPetName(String petName) {
		this.petName = petName;
		
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
		
	}
    
	public void saveMedRecord(ActionEvent event) {
		
		if (petID == null || ownerID == null || petName == null || ownerName == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Program says");
			alert.setHeaderText("Infomation is incomplete");
			alert.show();
			return;
		}
		
		
		String meddate = datePicker_MedDate.getValue().toString();
		String time = timePicker_Time.getValue() != null ? timePicker_Time.getValue().toString() : "";
		String medication = textField_Medication.getText().trim();
		String dosage = textField_Dosage.getText().trim();
		String frequency = textField_Frequency.getText().trim();
		String notes = textField_Notes.getText().trim();
		
		if (time.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Time is not selected.");
			alert.show();
			return;
		}
		
		if (medication.isEmpty() || dosage.isEmpty() || frequency.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Medication info is incomplete.");
			alert.show();
			return;
		}
    	
		
        try {
       
        	
        	if (model.addMedRecord(petID, meddate, time, medication, dosage, frequency, notes)) {
				
				
				log.logFile(null, "info", "Pet ID : " + petID + " medication record is added into database.");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Medication record is saved.");
				alert.show();
				
			}
			
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Medication record is not saved.");
				alert.show();
			}
        	
			
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
    }
	
}	
