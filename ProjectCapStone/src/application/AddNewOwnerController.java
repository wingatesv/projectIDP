package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class AddNewOwnerController {
	
	@FXML private TextField textField_firstName;
	@FXML private TextField textField_lastName;
	@FXML private TextField textField_phoneNumber;
	@FXML private TextField textField_icNumber;
	@FXML private TextField textField_address;
	
	public AddNewOwnerModel model = new AddNewOwnerModel();
	
	public void saveOwnerInfo(ActionEvent event) {
		String firstName = textField_firstName.getText();
		String lastName = textField_lastName.getText();
		String pNumber = textField_phoneNumber.getText();
		String address = textField_address.getText();
		
		
		try {
			
			
			if(model.addOwnerInfo(firstName, lastName, pNumber, pNumber, address))
			{

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner info is saved.");
				alert.show();
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
			}
			else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner info is not saved.");
				alert.show();
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

}
