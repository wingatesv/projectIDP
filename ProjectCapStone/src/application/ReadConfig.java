package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ReadConfig {
	
	private String newline = System.getProperty("line.separator");
	private String url;
	private String user;
	private String password;
	
	
	public ReadConfig()
	{

		try {
			
			
			 File config = new File ("ConfigTxt.txt");
			
				boolean exist = config.exists();
				if (!exist)
				{
					config.createNewFile();
					System.out.println("New Config txt is created");
					
					FileWriter writer = new FileWriter("ConfigTxt.txt");
					
								writer.write ("**************************** CONFIGURATION for CAPSTONE *******************************" + 
									newline + "***************************************************************************************" + 
									newline + "SQL Url :jdbc:mysql://localhost:3306/telemedicine_project" + 
									newline + "SQL Username :root" +
									newline + "SQL Password :" + 
									newline + "" +
									newline + "" +
									newline + "" +
									newline + "" +
									newline + "********************************* ALL RIGHT RESERVED *********************************");
					writer.close();
				}
			
				
				
				BufferedReader br = new BufferedReader(new FileReader(config));
				
					
				br.readLine();
				br.readLine();	
				
				String three = br.readLine();
				String urlString = three.substring(9);
			
	
				       
				String four = br.readLine();
				String userString = four.substring(four.lastIndexOf(":") + 1);
		
				       
			    String five = br.readLine();
			    String pwdString = five.substring(five.lastIndexOf(":") + 1);	   
				       
			
				
				 br.close();
				
				 this.url = urlString;
				 this.user = userString;
				 this.password = pwdString;
				 
		}			
		
		catch (Exception e)
		{
			e.printStackTrace();

		}
	}
	

	
	
	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}


}
