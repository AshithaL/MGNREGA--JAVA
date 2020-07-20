package mgnrega;

import java.sql.*;

public class Db_connection {
	public Connection con;
	
	public Connection connect() {
	try{  
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/mgnrega?autoReconnect=true&useSSL=false","root","nineleaps");  
	    System.out.println("Connection is created succcessfully");
	    return con;
	    }catch(Exception e){ System.out.println(e);}
	
		return null; 		
	}

}
