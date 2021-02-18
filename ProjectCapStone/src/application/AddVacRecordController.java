package application;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import common.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import model.AddVacRecordModel;
import javafx.scene.control.Alert.AlertType;

public class AddVacRecordController implements Initializable {
	
	@FXML private DatePicker datePicker_todayDate;
	@FXML private DatePicker datePicker_nextDate;
	@FXML private RadioButton radioButton_1st;
	@FXML private RadioButton radioButton_2nd;
	@FXML private RadioButton radioButton_3rd;
	@FXML private ComboBox<String> comboBox_vacType;
	
	
	private String petType;
	private Integer petID;
	
	public AddVacRecordModel model = new AddVacRecordModel();
	public Log log = new Log();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker_todayDate.setValue(LocalDate.now());	
		datePicker_nextDate.setValue(LocalDate.now());

	}
	
	public void setPetID(Integer petID) {
		this.petID = petID;
		
	}
	
	public void setPetType(String petType) throws SQLException {
		this.petType = petType;
		
		
		if (this.petType.matches("Dog")) {
			
			ObservableList<String> list = model.getVaccineDog();
			if (!list.isEmpty()) {
				comboBox_vacType.setItems(list);
			}
			
		}
		
		else if (this.petType.matches("Cat")) {
			ObservableList<String> list = model.getVaccineCat();
			if (!list.isEmpty()) {
				comboBox_vacType.setItems(list);
			}
		}
	}
	
	public void selectInjectionRadioButton(ActionEvent event) {
		
		if (radioButton_1st.isSelected() || radioButton_2nd.isSelected()) {
			datePicker_nextDate.setValue(LocalDate.now().plusMonths(1));
		}
		
		if (radioButton_3rd.isSelected()) {
			datePicker_nextDate.setValue(LocalDate.now().plusYears(1));
		}
		
	}
	
	
	public void saveVacRecord(ActionEvent event) {
		
		
		String date = datePicker_todayDate.getValue().toString();
		String nextDate = datePicker_nextDate.getValue().toString();
		String vaccine = comboBox_vacType.getValue();
		String injection = "";
		
		if (radioButton_1st.isSelected()) {
			injection = radioButton_1st.getText();
		}
		
		if (radioButton_2nd.isSelected()) {
			injection = radioButton_2nd.getText();
		}
		
		if (radioButton_3rd.isSelected()) {
			injection = radioButton_3rd.getText();
		}
		
		if (vaccine == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Vaccination info is incomplete.");
			alert.show();
			return;
		}
		
		try {
			
			if (model.addVacRecord(petID, date, nextDate, injection, vaccine)) {
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				log.logFile(null, "info", "Pet ID : " + petID + " vaccination record is added into database.");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Vaccination record is saved.");
				alert.show();
				
			}
			
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Vaccination record is not saved.");
				alert.show();
			}
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	
	

}
