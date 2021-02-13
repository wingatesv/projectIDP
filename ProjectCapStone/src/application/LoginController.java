package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class LoginController implements Initializable {
	
	@FXML private Label label_sqlStatus;
	@FXML private TextField textField_userName;
	@FXML private TextField textField_password;
	
	public LoginModel loginModel = new LoginModel();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (loginModel.isDbConnected()) {
			label_sqlStatus.setText("SQL Status : Connected");
		}
		else {
			label_sqlStatus.setText("SQL Status : Disconnected");
		}
		
	}
	
	public void login(ActionEvent event) {
		
		try {
			if (loginModel.isLogin(textField_userName.getText(), textField_password.getText())) {
				label_sqlStatus.setText("Credential is valid");
				
				((Node)event.getSource()).getScene().getWindow().hide();
				
				
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/Home.fxml").openStream());
				HomeController homeController = (HomeController)loader.getController();
				homeController.getUser(textField_userName.getText());
			
				
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				
				
				
			}
			else {
				label_sqlStatus.setText("Credential is invalid");
			}
			
		} catch (SQLException e) {
			label_sqlStatus.setText("Credential is invalid");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
