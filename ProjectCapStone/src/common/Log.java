package common;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Log {
	private String newline = System.getProperty("line.separator");
	
	public void logFile(Exception ex, String level, String msg)
	{
		   FileHandler fh = null;
	        try {
	            fh = new FileHandler("record.log",true);
	            Logger logger=Logger.getLogger("MYLOG");
	            logger.addHandler(fh);
	            SimpleFormatter formatter = new SimpleFormatter();  
	            fh.setFormatter(formatter);  
	            
	         
	            
		            switch (level)
		            {
		                case "severe":
		                    logger.log(Level.SEVERE, msg + newline, ex);
		                    if(!msg.equals(""))
		                    {
		                    	Alert alert = new Alert(AlertType.ERROR);
		    					alert.setTitle("Program says");
		    					alert.setHeaderText(msg);
		    					alert.show();
		                      
		                    }
		                    break;
		                    
		                case "warning":
		                    logger.log(Level.WARNING, msg + newline, ex);
		                    if(!msg.equals(""))
		                    {
		                    	Alert alert = new Alert(AlertType.WARNING);
		    					alert.setTitle("Program says");
		    					alert.setHeaderText(msg);
		    					alert.show();
		                    }    
		                    break;
		                case "info":
		                    logger.log(Level.INFO, msg + newline, ex);

		                    break;
		                
		            }
	        } 
		        catch (IOException | SecurityException ex1)
		        {
		            ex1.printStackTrace();
		        
				} 
	        			finally
				        {
				            if(fh!=null)fh.close();
				        }
	}

}
