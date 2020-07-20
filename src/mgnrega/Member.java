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
	
	private void view_profile() {
		// TODO Auto-generated method stub
		
	}

	private void complaint() {
		// TODO Auto-generated method stub
		
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
