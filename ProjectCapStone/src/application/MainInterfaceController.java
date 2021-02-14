package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MainInterfaceModel;

public class MainInterfaceController implements Initializable {
	
	// My Dash board tab
	@FXML private Label label_welcomeUser;
	@FXML private TabPane tabpane;
	
	// Pet Owner Tab
	@FXML private TextField textField_ownerFirstName;
	@FXML private TextField textField_ownerLastName;
	@FXML private TextField textField_ownerPhoneNumber;
	@FXML private TextField textField_ownerIcNumber;
	@FXML private TextField textField_ownerAddress;
	
	
	// Main Interface Model
	public MainInterfaceModel model = new MainInterfaceModel();
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	// Get current user in My dash board tab
	
	public void getUser(String user) {
		label_welcomeUser.setText("Welcome : " + user);
	}
	
	// Log out current user in My dash board tab
	
	public void logout(ActionEvent event) {
		
		try {
			
			((Node)event.getSource()).getScene().getWindow().hide();
			
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/fxml/Login.fxml").openStream());
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	// Go to Pet Owner tab in My dash board tab
	
	public void petOwner(ActionEvent event) {
		
		tabpane.getSelectionModel().select(1);
		
	}
	
	// add new owner info in Pet Owner tab
	
	public void saveOwnerInfo(ActionEvent event) {
		
		String firstName = textField_ownerFirstName.getText();
		String lastName = textField_ownerLastName.getText();
		String pNumber = textField_ownerPhoneNumber.getText();
		String icNumber = textField_ownerIcNumber.getText();
		String address = textField_ownerAddress.getText();
		
		
	
		
		try {
			

			if (firstName.isEmpty() || lastName.isEmpty() || pNumber.isEmpty() || icNumber.isEmpty() || address.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner info is incomplete.");
				alert.show();
				return;
			}
			
			
			if(model.addOwnerInfo(firstName, lastName, pNumber, icNumber, address))
			{
				

				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner info is saved.");
				alert.show();
				
				
				
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
	
	// Clear all text field in Pet Owner tab
	
	public void clearInfo(ActionEvent event) {
		textField_ownerFirstName.clear();
		textField_ownerLastName.clear();
		textField_ownerIcNumber.clear();
		textField_ownerAddress.clear();
		textField_ownerPhoneNumber.clear();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
