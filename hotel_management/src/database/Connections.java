package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Connections {
	public static Connection connect;
	public static PreparedStatement state;

	public static void openCon() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carethree", "root", "");
	}
	
	public static void closeCon() throws Exception{
		state.close();
		connect.close();
	}
	
}
