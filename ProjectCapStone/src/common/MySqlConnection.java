package common;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
	
	public static Connection Connector(String url, String user, String password) {
			
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection (url, user, password);
			return connection;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

}