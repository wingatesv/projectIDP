package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import common.Log;
import common.Owner;
import common.Pet;
import common.VaccineRecord;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
	@FXML private Tab calender;
	
	// Pet Owner Tab
	@FXML private TextField textField_ownerFirstName;
	@FXML private TextField textField_ownerLastName;
	@FXML private TextField textField_ownerPhoneNumber;
	@FXML private TextField textField_ownerIcNumber;
	@FXML private TextField textField_ownerAddress;
	@FXML private TextField textField_ownerEmail;
	
	// table view for pets in pet owner tab
	@FXML private TableView<Pet> petTable;
	@FXML private TableColumn<Pet, String> petName;
	@FXML private TableColumn<Pet, String> petType;
	@FXML private TableColumn<Pet, String> breed;
	@FXML private TableColumn<Pet, String> gender;
	@FXML private TableColumn<Pet, String> dob;
	@FXML private TableColumn<Pet, String> neutered;

	// Pet Info Tab
	@FXML private Label label_petName;
	@FXML private Label label_petType;
	@FXML private Label label_breed;
	@FXML private Label label_gender;
	@FXML private Label label_dob;
	@FXML private Label label_neutered;
	
	// Table View for Vaccination Record
	@FXML private TableView<VaccineRecord> vacRecordTable;
	@FXML private TableColumn<VaccineRecord, String> injection;
	@FXML private TableColumn<VaccineRecord, String> vaccineType;
	@FXML private TableColumn<VaccineRecord, String> date;
	@FXML private TableColumn<VaccineRecord, String> nextDate;
	
	// Calender Tab
	@FXML Pane calendarPane;
	@FXML ListView<String> appointmentListView;
	
	
	// Main Interface Model
	public MainInterfaceModel model = new MainInterfaceModel();
	
	// System log
	public Log log = new Log();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		  tabPane.getTabs().remove( petInfo );
		  tabPane.getTabs().remove( petOwner );
		  tabPane.getTabs().remove( calender );
		   
	}
	
/////////////////////////////////////////////// MY DASHBOARD TAB ////////////////////////////////////////////////	
	
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
	

	
// Opens Calender Tab
	public void goToCalender(ActionEvent event) {
		
		 if(!tabPane.getTabs().contains(calender)) {
			 
			   tabPane.getTabs().add(calender);  
			   tabPane.getSelectionModel().select(calender);
			   appointmentListView.setItems(null);
			   
			  }
		 tabPane.getSelectionModel().select(calender);
		
	
	
		
	}
	
	// Go to Pet Owner tab in My dash board tab
	
	public void petOwner(ActionEvent event) {
		
		 if(!tabPane.getTabs().contains(petOwner)) {
			 
			   tabPane.getTabs().add(petOwner);  
			   tabPane.getSelectionModel().select(petOwner);
			   petTable.setItems(null);
			   textField_ownerFirstName.clear();
			   textField_ownerLastName.clear();
			   textField_ownerIcNumber.clear();
			   textField_ownerPhoneNumber.clear();
			   textField_ownerAddress.clear();
			   
			  }
		 tabPane.getSelectionModel().select(petOwner);
		
	
	
		
	}
	
////////////////////////////////////////////////PET OWNER TAB //////////////////////////////////////////////////	
	
	// add new owner info in Pet Owner tab
	
	public void saveOwnerInfo(ActionEvent event) {
		
		String firstName = textField_ownerFirstName.getText().trim();
		String lastName = textField_ownerLastName.getText().trim();
		String pNumber = textField_ownerPhoneNumber.getText().trim();
		String icNumber = textField_ownerIcNumber.getText().trim();
		String address = textField_ownerAddress.getText();
		String email = textField_ownerEmail.getText().trim();
		
		
	
		
		try {
			

			if (firstName.isEmpty() || lastName.isEmpty() || pNumber.isEmpty() || icNumber.isEmpty() || address.isEmpty() || email.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner info is incomplete.");
				alert.show();
				return;
			}
			
			
			if(model.addOwnerInfo(firstName, lastName, icNumber, pNumber, address, email))
			{
				

				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner info is saved.");
				alert.show();
				log.logFile(null, "info", firstName + " " + lastName + " is registered into database.");
				
				
				
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
	
	// Search for owner name based on Name
	
	public void searchOwner(ActionEvent event) {
		
		String icNumber = textField_ownerIcNumber.getText().trim();
	
		try {
			
			if (icNumber.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Please key in IC Number");
				alert.show();
				return;
			}
			
			ObservableList<Owner> owners = model.searchOwner(icNumber);
			if (owners.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Owner not found");
				alert.show();
				textField_ownerFirstName.clear();
				textField_ownerLastName.clear();
				return;
			}
			
			textField_ownerFirstName.setText(owners.get(0).getFirstName());
			textField_ownerLastName.setText(owners.get(0).getLastName());
			textField_ownerPhoneNumber.setText(owners.get(0).getTelephoneNumber());
			textField_ownerAddress.setText(owners.get(0).getAddress());
			textField_ownerEmail.setText(owners.get(0).getEmail());
			
			
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	// Edit Owner info based on IC Number -> Owner ID
	
	public void editOwnerInfo(ActionEvent event) {
		
		 Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		 alertConfirm.setTitle("Program says");
		 alertConfirm.setHeaderText("IC Number should not be edited.");
		 alertConfirm.setContentText("Are you ok with this?");

         Optional<ButtonType> result = alertConfirm.showAndWait();
         if (result.get() == ButtonType.OK){
           
        	String firstName = textField_ownerFirstName.getText().trim();
     		String lastName = textField_ownerLastName.getText().trim();
     		String pNumber = textField_ownerPhoneNumber.getText().trim();
     		String icNumber = textField_ownerIcNumber.getText().trim();
     		String address = textField_ownerAddress.getText();
     		String	email = textField_ownerEmail.getText().trim();
     		
     		try {

     			if (firstName.isEmpty() || lastName.isEmpty() || pNumber.isEmpty() || icNumber.isEmpty() || address.isEmpty() || email.isEmpty()) {
     				Alert alert = new Alert(AlertType.ERROR);
     				alert.setTitle("Program says");
     				alert.setHeaderText("Owner info is incomplete.");
     				alert.show();
     				return;
     			}
     			
     			Integer OwnerID = model.getOwnerID(icNumber);
				
				if (OwnerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("IC Number is changed.");
					alert.show();
					petTable.setItems(null);
					return;
				}
				
				if (model.editOwnerInfo(firstName, lastName, pNumber, address, email, OwnerID)) {
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Program says");
					alert.setHeaderText("Owner info is edited.");
					alert.show();
					log.logFile(null, "info", firstName + " " + lastName + " is edited.");
				}
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Owner info is not edited.");
					alert.show();
				}
    	
     		} catch (Exception e) {
     			log.logFile(e, "severe", e.getMessage());
     			e.printStackTrace();
     		}
     	 
        	 
         }
	}
	
	// Clear all text field in Pet Owner tab
	
	public void clearInfo(ActionEvent event) {
		textField_ownerFirstName.clear();
		textField_ownerLastName.clear();
		textField_ownerIcNumber.clear();
		textField_ownerAddress.clear();
		textField_ownerPhoneNumber.clear();
		textField_ownerEmail.clear();
		
	}
	
	public void refreshPetTable(ActionEvent event) {
		
		String icNumber = textField_ownerIcNumber.getText().trim();
	
		
		try {	
			
				if (icNumber.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Pet Owner IC is missing.");
					alert.show();
					petTable.setItems(null);
					return;
				}
			
				
				Integer OwnerID = model.getOwnerID(icNumber);
				
				if (OwnerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Pet Owner not found in database");
					alert.show();
					petTable.setItems(null);
					return;
				}
			
			
				ObservableList<Pet> petList = model.getPetList(OwnerID);
				
				if (petList.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("No Record found.");
					alert.show();
					petTable.setItems(null);
					return;
				}
				
				petName.setCellValueFactory(new PropertyValueFactory<Pet, String>("PetName"));
				petType.setCellValueFactory(new PropertyValueFactory<Pet, String>("PetType"));
				breed.setCellValueFactory(new PropertyValueFactory<Pet, String>("Breed"));
				gender.setCellValueFactory(new PropertyValueFactory<Pet, String>("Gender"));
				dob.setCellValueFactory(new PropertyValueFactory<Pet, String>("dob"));
				neutered.setCellValueFactory(new PropertyValueFactory<Pet, String>("Neutered"));
				
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
				
				if (icNumber.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Pet Owner IC is missing.");
					alert.show();
					return;
				}
				
				Integer ownerID = model.getOwnerID(icNumber);
				
				
				if (ownerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Pet Owner not found in database");
					alert.show();
					return;
				}
			
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/fxml/AddEditPet.fxml").openStream());
				AddEditPetController addEditPetController = (AddEditPetController)loader.getController();
				addEditPetController.setOwnerID(ownerID);
				addEditPetController.setMode("ADD");
			
				
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
			
			
				String icNumber = textField_ownerIcNumber.getText().trim();
				
				if (icNumber.isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Pet Owner IC is missing.");
					alert.show();
					return;
				}
				
				Integer ownerID = model.getOwnerID(icNumber);
				
				if (ownerID == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("Pet Owner not found in database");
					alert.show();
					return;
				}
				
				 	ObservableList<Pet> petTableList = petTable.getSelectionModel().getSelectedItems();		// get all selected items
				
					for(Pet item : petTableList)
					{
						petName = item.getPetName();
						petType = item.getPetType();
						
						
					}
			
				 if(!tabPane.getTabs().contains(petInfo) && !petName.isEmpty()) {
					 
					   tabPane.getTabs().add(petInfo);  
					   tabPane.getSelectionModel().select(petInfo);
					   vacRecordTable.setItems(null);
					   
					  }
				 
				 Integer petID = model.getPetID(ownerID, petName, petType);
					
					if (petID == null) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Program says");
						alert.setHeaderText("Pet is not registered.");
						alert.show();
						return;
					}
				 
				 ObservableList<Pet> petList = model.getPetInfo(petID);
				 
				 if (petList.isEmpty()) {
					 Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Program says");
						alert.setHeaderText("No pet found");
						alert.show();
						return;
				 }
				 label_petName.setText("Pet Name : " + petList.get(0).getPetName());
				 label_breed.setText("Breed : " + petList.get(0).getBreed());
				 label_petType.setText("Pet Type : " + petList.get(0).getPetType());
				 label_gender.setText("Gender : " + petList.get(0).getGender());
				 label_dob.setText("DOB : " + petList.get(0).getDob());
				 label_neutered.setText("Neutered : " + petList.get(0).getNeutered());
	
			}
			
		} catch (SQLException e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		
		}

		
	}
	
////////////////////////////////////////////////PET INFO TAB //////////////////////////////////////////////////		
	

	// Edit pet info
	public void editPetInfo(ActionEvent event) {
		if (label_petName.getText().length() < 11 || label_petType.getText().length() < 11 ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Pet Record is empty");
			alert.show();
			return;
		}
		
		
		String petType = label_petType.getText().substring(11);
		String petName = label_petName.getText().substring(11);
		String icNumber = textField_ownerIcNumber.getText().trim();
		
		if (icNumber.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Pet Owner IC is missing.");
			alert.show();
			return;
		}
		try {
			
				Integer ownerID = model.getOwnerID(icNumber);
			
			
			if (ownerID == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet Owner not found in database");
				alert.show();
				return;
			}
			
			Integer petID = model.getPetID(ownerID, petName, petType);
			
			if (petID == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet is not registered.");
				alert.show();
				return;
			}
			
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/fxml/AddEditPet.fxml").openStream());
			AddEditPetController addEditPetController = (AddEditPetController)loader.getController();
			addEditPetController.setOwnerID(ownerID);
			addEditPetController.setMode("EDIT");
			addEditPetController.setPetID(petID);
		
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Delete pet info and vaccination record
	
	public void deletePetInfo(ActionEvent event) {

		 Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		 alertConfirm.setTitle("Program says");
		 alertConfirm.setHeaderText("Are you sure you want to delete Pet Info?");
		 alertConfirm.setContentText("This action will erase all data including pet vaccination record.");

        Optional<ButtonType> result = alertConfirm.showAndWait();
        if (result.get() == ButtonType.OK){
        	
        	try {
        		
        		if (label_petName.getText().length() < 11 || label_petType.getText().length() < 11 ) {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Program says");
        			alert.setHeaderText("Pet Record is empty");
        			alert.show();
        			return;
        		}
        		
        		
        		String petType = label_petType.getText().substring(11);
        		String petName = label_petName.getText().substring(11);
        		String icNumber = textField_ownerIcNumber.getText().trim();
        		
        		if (icNumber.isEmpty()) {
        			Alert alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Program says");
        			alert.setHeaderText("Pet Owner IC is missing.");
        			alert.show();
        			return;
        		}
        		
        		
        			
        			Integer ownerID = model.getOwnerID(icNumber);
        			
        			
        			if (ownerID == null) {
        				Alert alert = new Alert(AlertType.ERROR);
        				alert.setTitle("Program says");
        				alert.setHeaderText("Pet Owner not found in database");
        				alert.show();
        				return;
        			}
        			
        			Integer petID = model.getPetID(ownerID, petName, petType);
        			
        			if (petID == null) {
        				Alert alert = new Alert(AlertType.ERROR);
        				alert.setTitle("Program says");
        				alert.setHeaderText("Pet is not registered.");
        				alert.show();
        				return;
        			}
        			
        			
        			if (model.deletePetInfo(petID)) {
						
        				Alert alert = new Alert(AlertType.INFORMATION);
        				alert.setTitle("Program says");
        				alert.setHeaderText("Pet info is deleted.");
        				alert.show();
        				log.logFile(null, "info", "PetID : " + petID + " is deleted into from pets.");
        				
					}
	        			
	        			else {
	        				Alert alert = new Alert(AlertType.ERROR);
	            			alert.setTitle("Program says");
	            			alert.setHeaderText("Pet info is not deleted.");
	            			alert.show();
						}
        			
        			if (model.deleteVacRecord(petID)) {
        				Alert alert = new Alert(AlertType.INFORMATION);
        				alert.setTitle("Program says");
        				alert.setHeaderText("Pet info is deleted.");
        				alert.show();
        				log.logFile(null, "info", " Vaccination record of PetID : " + petID + " is deleted.");
        				
					}
	        			else {
	        				Alert alert = new Alert(AlertType.ERROR);
	            			alert.setTitle("Program says");
	            			alert.setHeaderText("Vaccination record is not deleted.");
	            			alert.show();
						}
    			      	
			} catch (Exception e) {
				log.logFile(e, "severe", e.getMessage());
				e.printStackTrace();
			}
        		
        }
		
	}
	
	// Add vaccination Record at Pet Info Tab
	
	public void addVaccinationRecord(ActionEvent event) {
		
		if (label_petName.getText().length() < 11 || label_petType.getText().length() < 11 ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Pet Record is empty");
			alert.show();
			return;
		}
		
		
		String petType = label_petType.getText().substring(11);
		String petName = label_petName.getText().substring(11);
		String icNumber = textField_ownerIcNumber.getText().trim();
		String ownerName = textField_ownerFirstName.getText().trim() + " " + textField_ownerLastName.getText().trim();
		
		if (icNumber.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Pet Owner IC is missing.");
			alert.show();
			return;
		}
		
		try {
			
			Integer ownerID = model.getOwnerID(icNumber);
			
			
			if (ownerID == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet Owner not found in database");
				alert.show();
				return;
			}
			
			Integer petID = model.getPetID(ownerID, petName, petType);
			
			if (petID == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet is not registered.");
				alert.show();
				return;
			}
			
			
			
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/fxml/AddVacRecord.fxml").openStream());
			AddVacRecordController addVacRecordController = (AddVacRecordController)loader.getController();
			addVacRecordController.setPetType(petType);
			addVacRecordController.setPetID(petID);
			addVacRecordController.setPetName(petName);
			addVacRecordController.setOwnerName(ownerName);
			addVacRecordController.setOwnerID(ownerID);
		
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	public void refreshVacRecordTable(ActionEvent event) {
		
		if (label_petName.getText().length() < 11 || label_petType.getText().length() < 11 ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Pet record is empty");
			alert.show();
			return;
		}
		
		String petType = label_petType.getText().substring(11);
		String petName = label_petName.getText().substring(11);
		String icNumber = textField_ownerIcNumber.getText().trim();
		
		if (icNumber.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Program says");
			alert.setHeaderText("Pet Owner IC is missing.");
			alert.show();
			vacRecordTable.setItems(null);
			return;
		}
		
		try {
			
			Integer ownerID = model.getOwnerID(icNumber);
			
			
			if (ownerID == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet Owner not found in databasel");
				alert.show();
				vacRecordTable.setItems(null);
				return;
			}
			
			Integer petID = model.getPetID(ownerID, petName, petType);
			
			if (petID == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("Pet is not registered.");
				alert.show();
				vacRecordTable.setItems(null);
				return;
			}
			
			ObservableList<VaccineRecord> vaccineRecords = model.getVaccineRecord(petID);
			
			if (vaccineRecords.isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Program says");
				alert.setHeaderText("No Record found.");
				alert.show();
				vacRecordTable.setItems(null);
				return;
			}
			
			
			injection.setCellValueFactory(new PropertyValueFactory<VaccineRecord, String>("Injection"));
			vaccineType.setCellValueFactory(new PropertyValueFactory<VaccineRecord, String>("VaccineType"));
			date.setCellValueFactory(new PropertyValueFactory<VaccineRecord, String>("Date"));
			nextDate.setCellValueFactory(new PropertyValueFactory<VaccineRecord, String>("NextDate"));
			
			vacRecordTable.setItems(vaccineRecords);
			
			
			
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
////////////////////////////////////////////////CALENDER TAB //////////////////////////////////////////////////		
	
	// refresh appointment list in calander tab
	public void refreshAppointmentList(ActionEvent event) {
		try {
			 ObservableList<String> appointmentList = model.getAppointmentList();
			 
			 if (appointmentList.isEmpty()) {
				 Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Program says");
					alert.setHeaderText("No record found");
					alert.show();
					return;
			 }
			 
			 appointmentListView.setItems(appointmentList);
			 
			 
			 
			 
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	public void goToAppointmentInfo(MouseEvent event) {
		try {
			
				
			if (event.getClickCount() == 2) {
				
				if (!appointmentListView.getSelectionModel().isEmpty()) {
					Integer appID = Integer.parseInt(appointmentListView.getSelectionModel().getSelectedItem().substring(0, 1));
					Stage primaryStage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					Pane root = loader.load(getClass().getResource("/fxml/AppointmentInfo.fxml").openStream());
					AppointmentInfoController appointmentInfoController = (AppointmentInfoController)loader.getController();
					appointmentInfoController.setAppID(appID);
				
					
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				}
				else {
					 Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Program says");
						alert.setHeaderText("No record is selected.");
						alert.show();
				}
				
				
			}
				
			
		} catch (Exception e) {
			log.logFile(e, "severe", e.getMessage());
			e.printStackTrace();
		}
	}
	

	
}
