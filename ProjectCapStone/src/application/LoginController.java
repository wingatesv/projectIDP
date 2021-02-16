package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import common.Log;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.LoginModel;


public class LoginController implements Initializable {
	
	@FXML private Label label_sqlStatus;
	@FXML private TextField textField_userName;
	@FXML private TextField textField_password;
	
	public LoginModel loginModel = new LoginModel();
	public Log log = new Log();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isDbConnected()) {
			label_sqlStatus.setText("SQL Status : Connected");
			log.logFile(null, "info", "Connected to mySQL database");
		
		}
		else {
			label_sqlStatus.setText("SQL Status : Disconnected");
			log.logFile(null, "warning", "Not connected to mySQL database");
		}
		
	}
	
	public void login(ActionEvent event) {
		
		try {
			if (loginModel.isLogin(textField_userName.getText(), textField_password.getText())) {
				label_sqlStatus.setText("Credential is valid");
				log.logFile(null, "info", textField_userName.getText() + " log in successfully.");
				
				
					((Node)event.getSource()).getScene().getWindow().hide();
					
					
					Stage primaryStage = new Stage();
					FXMLLoader loader = new FXMLLoader();
					Pane root = loader.load(getClass().getResource("/fxml/MainInterface.fxml").openStream());
					MainInterfaceController mainInterfaceController = (MainInterfaceController)loader.getController();
					mainInterfaceController.getUser(textField_userName.getText());
				
					
					Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
				
			}
			else {
				label_sqlStatus.setText("Credential is invalid");
				log.logFile(null, "warning", label_sqlStatus.getText() + " for " + textField_userName.getText());
				textField_userName.clear();
				textField_password.clear();
			}
			
		} catch (SQLException e) {
			
			label_sqlStatus.setText("Connection error.");
			log.logFile(e, "severe", e.getMessage()  + " for " + textField_userName.getText());
			textField_userName.clear();
			textField_password.clear();
			e.printStackTrace();
		} catch (IOException e) {
			
			log.logFile(e, "severe", e.getMessage()  + " for " + textField_userName.getText());
			textField_userName.clear();
			textField_password.clear();
			e.printStackTrace();
		}
		
	}

}
