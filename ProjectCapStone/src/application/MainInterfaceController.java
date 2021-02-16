package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import common.Log;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.MainInterfaceModel;

public class MainInterfaceController implements Initializable {
	
	// My Dash board tab
	@FXML private Label label_welcomeUser;
	@FXML private TabPane tabPane;
	@FXML private Tab myDashBoard;
	@FXML private Tab petOwner;
	@FXML private Tab petInfo;
	
	// Pet Owner Tab
	@FXML private TextField textField_ownerFirstName;
	@FXML private TextField textField_ownerLastName;
	@FXML private TextField textField_ownerPhoneNumber;
	@FXML private TextField textField_ownerIcNumber;
	@FXML private TextField textField_ownerAddress;
	
	// table view for pets in pet owner tab
	@FXML private TableView<Pet> petTable;
	@FXML private TableColumn<Pet, String> petName;
	@FXML private TableColumn<Pet, String> petType;
	@FXML private TableColumn<Pet, String> breed;
	@FXML private TableColumn<Pet, String> gender;
	@FXML private TableColumn<Pet, String> dob;
	
	// Pet Info Tab
	@FXML private Label label_petName;
	@FXML private Label label_petType;
	@FXML private Label label_breed;
	@FXML private Label label_gender;
	@FXML private Label label_dob;
	
	
	// Main Interface Model
	public MainInterfaceModel model = new MainInterfaceModel();
	
	// System log
	public Log log = new Log();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		  tabPane.getTabs().remove( petInfo );
		  tabPane.getTabs().remove( petOwner );
		   
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
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	
	// Go to Pet Owner tab in My dash board tab
	
	public void petOwner(ActionEvent event) {
		
		 if(!tabPane.getTabs().contains(petOwner)) {
			 
			   tabPane.getTabs().add(petOwner);  
			   tabPane.getSelectionModel().select(petOwner);
			   
			  }
		
	
	
		
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
			log.logFile(e, "severe", e.getMessage());
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
	
	public void refreshPetTable(ActionEvent event) {
		
		String icNumber = textField_ownerIcNumber.getText();
	
		
		try {	
			
				
				Integer OwnerID = model.getOwnerID(icNumber);
				
				if (OwnerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Owner ID is null");
					alert.show();
					petTable.setItems(null);
					return;
				}
			
			
				ObservableList<Pet> petList = model.getPetList(OwnerID);
				petName.setCellValueFactory(new PropertyValueFactory<Pet, String>("PetName"));
				petType.setCellValueFactory(new PropertyValueFactory<Pet, String>("PetType"));
				breed.setCellValueFactory(new PropertyValueFactory<Pet, String>("Breed"));
				gender.setCellValueFactory(new PropertyValueFactory<Pet, String>("Gender"));
				dob.setCellValueFactory(new PropertyValueFactory<Pet, String>("dob"));
				
				petTable.setItems(petList);
			
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
	}
	
	// add new pet from  button
	
	public void addPet(ActionEvent event) {
		
		String icNumber = textField_ownerIcNumber.getText();
		
	
		try {
			
				Integer ownerID = model.getOwnerID(icNumber);
				
				
				if (ownerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Owner ID is null");
					alert.show();
					return;
				}
			
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/fxml/AddPet.fxml").openStream());
				AddPetController addPetController = (AddPetController)loader.getController();
				addPetController.setOwnerID(ownerID);
			
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}
	

// double click on the table row to go to pet info	
	public void goToPetInfo(MouseEvent event) {
		try {
			
			if(event.getClickCount() == 2)
			{
				String petName = "";
				String petType = "";
				String dob = "";
			
				String icNumber = textField_ownerIcNumber.getText();
				
				Integer OwnerID = model.getOwnerID(icNumber);
				
				if (OwnerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Owner ID is null");
					alert.show();
					return;
				}
				
				 	ObservableList<Pet> petTableList = petTable.getSelectionModel().getSelectedItems();		// get all selected items
				
					for(Pet item : petTableList)
					{
						petName = item.getPetName();
						petType = item.getPetType();
						dob = item.getDob();
						
					}
			
				 if(!tabPane.getTabs().contains(petInfo)) {
					 
					   tabPane.getTabs().add(petInfo);  
					   tabPane.getSelectionModel().select(petInfo);
					   
					  }
				 
				 ObservableList<Pet> petList = model.getPetInfo(OwnerID, petName, petType, dob);
				 label_petName.setText("Pet Name : " + petList.get(0).getPetName());
				 label_breed.setText("Breed : " + petList.get(0).getBreed());
				 label_petType.setText("Pet Type : " + petList.get(0).getPetType());
				 label_gender.setText("Gender : " + petList.get(0).getGender());
				 label_dob.setText("DOB : " + petList.get(0).getDob());
				 /*
				 	
				 Map<String, Object> petInfo = new HashMap<>();
				 petInfo = model.getPetInfo(OwnerID, petName);
				 
				 label_petName.setText("Pet Name : " + petInfo.get("PetName"));
				 label_breed.setText("Breed : " + petInfo.get("Breed"));
				 label_petType.setText("Pet Type : " + petInfo.get("PetType"));
				 label_gender.setText("Gender : " + petInfo.get("Gender"));
				 label_dob.setText("DOB : " + petInfo.get("DOB"));
				 
				 System.out.println(petInfo.get("PetID"));
	*/
	
			}
			
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		
		}
		
		
	
		
	}
	

	
}
