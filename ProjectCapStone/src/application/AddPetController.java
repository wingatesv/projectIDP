package application;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import model.AddPetModel;

public class AddPetController {
	
	@FXML private TextField textField_petName;
	@FXML private TextField textField_breed;
	
	@FXML private RadioButton radioButton_dog;
	@FXML private RadioButton radioButton_cat;
	@FXML private RadioButton radioButton_other;
	@FXML private RadioButton radioButton_male;
	@FXML private RadioButton radioButton_female;
	
	@FXML private DatePicker datePicker_dob;

	
	private Integer ownerID;	

	public AddPetModel model = new AddPetModel();

	public void setOwnerID(Integer ownerID) {
		this.ownerID = ownerID;
		
	}
	
	public void savePetInfo(ActionEvent event) {
		
		String petName = textField_petName.getText();
		String breed = textField_breed.getText();
		String petType = "";
		String gender = "";
		String dob = "";
		
		if (radioButton_dog.isSelected()) {
			petType = radioButton_dog.getText();
		}
		
		if (radioButton_cat.isSelected()) {
			petType = radioButton_cat.getText();
		}
		
		if (radioButton_other.isSelected()) {
			petType = radioButton_other.getText();
		}
		
		if (radioButton_male.isSelected()) {
			gender = radioButton_male.getText();
		}
		
		if (radioButton_female.isSelected()) {
			gender = radioButton_female.getText();
		}
		
		LocalDate lDate = datePicker_dob.getValue();
		dob = 	lDate.toString();
	
		
	
		
		try {
			
			
			if (petName.isEmpty() || breed.isEmpty()) {
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet info is incomplete.");
				alert.show();
				return;
				
			}
			
			if (model.addPetInfo(ownerID, petName, petType, breed, gender, dob)) {
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet info is saved.");
				alert.show();
				
				
			}
			
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet info is not saved.");
				alert.show();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
