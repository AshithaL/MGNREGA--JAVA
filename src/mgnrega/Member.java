package mgnrega;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class Member {
	
	Db_connection db = new Db_connection();
	Connection con = db.connect();
    Scanner sc_obj = new Scanner(System.in);
    
    public void functions() throws SQLException, ParseException, IOException {
        System.out.print("Choose an appropriate option. \n "
        		+ "1. View your profile \n"
        		+ "2. Have any Complaint? \n");
        
        int option =sc_obj.nextInt();
        
        switch(option)
        {
            case 1:
                view_profile();
                break;
            case 2:
                complaint();
              
                break;
            default:
                System.out.println("Default ");
        }

    }
	
	public void view_profile() throws SQLException, ParseException, IOException {
		try {
            Statement statement = con.createStatement();
            System.out.println("Enter Member id");
			String mid = sc_obj.nextLine();
            ResultSet res = statement.executeQuery("select * from Member where id = '" + mid + "'");
            while (res.next()) {
                System.out.println("1.Member Id: " + res.getInt(1));
                System.out.println("2.Name: " + res.getString(2));
                System.out.println("3.Email_id: " + res.getString(3));
                System.out.println("4.Area: " + res.getString(5));
                System.out.println("5.Pincode: " + res.getInt(6));        
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            functions();
        }
		
	}

	private void complaint() throws SQLException, ParseException, IOException {
		try {
            Statement statement = con.createStatement();
            sc_obj.nextLine();
            System.out.println("Enter Member id");
			String mid = sc_obj.nextLine();
            System.out.println("Write Complaint:");
            String complaint = sc_obj.nextLine();
            System.out.println("Enter GPM id");
			String gid = sc_obj.nextLine();
            statement.execute("insert into Complaints(Mid,Complaint,Gpm_id)" +
                    "values('" + mid + "','" + complaint + "','" + gid + "')");
            System.out.println("Complaint filed successfully!");
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error is:" + e.getMessage());
        } finally {
            functions();
        }
		
	}

	public void member_login() throws SQLException {
		try {
            Statement statement = con.createStatement();
            System.out.print("Enter Email_Id: ");
            String Email = sc_obj.nextLine();
            System.out.print("Enter Password: ");
            String Password = sc_obj.nextLine();
                ResultSet rs = statement.executeQuery("select * from Member where Email_id = '" + Email + "'  AND password = '" + Password + "'");
                if (rs.next()) {
                    System.out.print("WELCOME \n Choose from the below options");
                    functions();
                } else
                    System.out.print("EmailId/Password is incorrect");
        } catch (Exception e) {
            System.out.print(e);
        }
	}

}
