package mgnrega;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

public class GPM {
	Db_connection db = new Db_connection();
	Connection con = db.connect();
    Scanner sc_obj = new Scanner(System.in);
	
    public void functions() throws SQLException, ParseException, IOException {
        System.out.print("Choose an appropriate option. \n "
        		+ "1. Create Member \n "
        		+ "2. Assign Member \n "
        		+ "3. Update Member \n "
        		+ "4. Delete Member \n "
        		+ "5. Issue Job Card \n"
        		+ "6. Work allotment \n");
        
        int option =sc_obj.nextInt();
        
        switch(option) {
       
            case 1:
            	create_member();
                break;
            case 2:
                assign_member();
                break;
            case 3:
                update_member();
                break;
            case 4:
                delete_member();
                break;
            case 5:
                updateProject();
                break;
            case 6:
                issue_job_card();
                break;
                
            case 7:
            	work_allot();
            	break;
            default:
                System.out.println("Default ");
        }

    }
    
	private void create_member() {
		// TODO Auto-generated method stub
		
	}

	private void assign_member() {
		// TODO Auto-generated method stub
		
	}

	private void update_member() {
		// TODO Auto-generated method stub
		
	}

	private void delete_member() {
		// TODO Auto-generated method stub
		
	}

	private void updateProject() {
		// TODO Auto-generated method stub
		
	}

	private void issue_job_card() {
		// TODO Auto-generated method stub
		
	}

	private void work_allot() {
		// TODO Auto-generated method stub
		
	}

	public void gpm_login() throws SQLException {
		try {
            Statement statement = con.createStatement();
            System.out.print("Enter Email_Id: ");
            String Email = sc_obj.nextLine();
            System.out.print("Enter Password: ");
            String Password = sc_obj.nextLine();
                ResultSet rs = statement.executeQuery("select * from GPM where Email_id = '" + Email + "'  AND password = '" + Password + "'");
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
