package common;



import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;

import application.AppointmentListController;



/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;
    public Log log = new Log();

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> {
        	
        	if (e.getClickCount() == 2) {
        		
        		
            	try {
            		
            		Stage primaryStage = new Stage();
        			FXMLLoader loader = new FXMLLoader();
        			Pane root = loader.load(getClass().getResource("/fxml/AppointmentList.fxml").openStream());
        			AppointmentListController appointmentListController = (AppointmentListController)loader.getController();
        			appointmentListController.setDate(date);
        			
        			Scene scene = new Scene(root);
        			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        			primaryStage.setScene(scene);
        			primaryStage.show();
                
                
                
                
                
    			} catch (Exception e2) {
    				e2.printStackTrace();
    				log.logFile(e2, "severe", e2.getMessage());
    			}
            	
			}
        	
        	
        
        });
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

