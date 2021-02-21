package application;

import java.sql.SQLException;
import java.time.LocalDate;

import common.AppointmentInfo;
import common.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.AppointmentInfoModel;
import javafx.scene.control.Alert.AlertType;

public class AppointmentInfoController {
	
	@FXML private Label label_petOwner;
	@FXML private Label label_petName;
	@FXML private Label label_vaccination;
	@FXML private Label label_status;
	@FXML private DatePicker datePicker_date;
	
	private Integer appID;
	
	public AppointmentInfoModel model = new AppointmentInfoModel();
	public Log log = new Log();


	public void setAppID(Integer appID) {
		this.appID = appID;
		
		try {
			if (this.appID != null) {
				
				 ObservableList<AppointmentInfo> appointmentList = model.getAppointmentInfo(appID);
				 
				 if (appointmentList.isEmpty()) {
					 Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Program says");
						alert.setHeaderText("No appointment info found");
						alert.show();
						return;
				 }
				 label_petOwner.setText("Owner Name : " + appointmentList.get(0).getOwnerName());
				 label_petName.setText("Pet Name : " + appointmentList.get(0).getPetName());
				 label_vaccination.setText("Details : " + appointmentList.get(0).getInjection() + " " + appointmentList.get(0).getVaccine());
				 label_status.setText("Status : " + appointmentList.get(0).getStatus());
				 datePicker_date.setValue(LocalDate.parse(appointmentList.get(0).getDate()));
				
				
				
				
				
				
				
			}	
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void completeAppointment(ActionEvent event) {
		
	try {
				
				if (model.completeAppointment(appID)) {
					
					
					log.logFile(null, "info", "Appointment is completed");
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Program says");
					alert.setHeaderText("Appointment is completed");
					alert.show();
					
					((Node)event.getSource()).getScene().getWindow().hide();
					
				}
				
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Appointment is not completed.");
					alert.show();
				}
				
				
				
				
			} catch (SQLException e) {
				log.logFile(e, "severe", e.getMessage());
				e.printStackTrace();
			}
	}
	
	public void editAppointmentDate(ActionEvent event) {
		String date = datePicker_date.getValue().toString();
		
		if (date== null) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("Program says");
			alertError.setHeaderText("Date is not selected");
			alertError.show();
			return;
		}
		
		try {
			
			if (model.editAppointmentDate(appID, date)) {
				
				
				log.logFile(null, "info", "Appointment date is updated");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Appointment date is updated");
				alert.show();
				
			}
			
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Appointment date is not updated");
				alert.show();
			}
			
			
			
			
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
		}	

}
