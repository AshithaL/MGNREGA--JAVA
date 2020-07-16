package mgnrega;

import java.sql.*;  

public class Db_connection {
	public static void main(String args[]) {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/mgnrega?autoReconnect=true&useSSL=false","root","nineleaps");  
	        System.out.println("Connection is created succcessfully");

			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("show tables"); 
			System.out.println("The tables in mgnrega db are -");
			while (rs.next() ) 
			System.out.println(rs.getString("Tables_in_mgnrega"));
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
	}

}
