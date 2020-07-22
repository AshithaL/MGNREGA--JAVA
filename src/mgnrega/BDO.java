package mgnrega;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.Scanner;

public class BDO {
	
	Db_connection db = new Db_connection();
	Connection con = db.connect();
    Scanner sc_obj = new Scanner(System.in);
	
    public void functions() throws SQLException, ParseException, IOException {
        System.out.print("Choose an appropriate option. \n "
        		+ "1.Create Gpm \n "
        		+ "2.Update Gpm \n "
        		+ "3.Delete Gpm \n "
        		+ "4.Create Project \n "
        		+ "5.Update Project \n "
        		+ "6.Delete Project\n");
        
        int option =sc_obj.nextInt();
        
        switch(option)
        {
            case 1:
                createGpm();
                break;
            case 2:
                updateGpm();
                break;
            case 3:
                deleteGpm();
                break;
            case 4:
                createProject();
                break;
            case 5:
                updateProject();
                break;
            case 6:
                deleteProject();
                break;
            default:
                System.out.println("Default ");
        }

    }
	private void deleteProject() {
		try {
            Statement statement = con.createStatement();
            System.out.print("Enter Project Id to delete: ");
            int id = sc_obj.nextInt();
            statement.executeUpdate("Delete from Project where Id = '"+id+"'");
            con.close();
            System.out.print("Done");
           
        } catch (Exception e) {
            System.out.print(e);
        }
		
	}
	private void updateProject() throws IOException, SQLException, ParseException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			Statement statement = con.createStatement();
			System.out.print("Enter Project Id: ");
			String id = bufferedReader.readLine();

			ResultSet pid = statement.executeQuery("select * from Project where Id = '" + id + "' ");

			System.out.println("What would you want to update ?\n"
					+ "1. Total Members \n"
					+ "2. Cost estimated \n"
					+ "3. Is allocated to \n");

			int cg = sc_obj.nextInt();

			switch (cg) {

			case 1:
				System.out.println("Enter Total members:");
				String tot = bufferedReader.readLine();
				statement.executeUpdate("Update Project set Total_members ='" + tot + "' where Id = '" + id + "' ");
				con.close();
				System.out.println("Done");
				break;

			case 2:
				System.out.println("Enter Cost estimated:");
				String ce = bufferedReader.readLine();
				statement.executeUpdate("Update Project set Cost_estimated ='" + ce + "' where Id = '" + id + "' ");
				System.out.println("Cost estimated has changed");
				con.close();
				break;

			case 3:
				System.out.println("Enter to allocate");
				String alloc = bufferedReader.readLine();
				statement.executeUpdate("Update Project set Area ='" + alloc + "' where Id = '" + id + "' ");
				System.out.println("Done");
				con.close();
				break;

			default:
				System.out.println("Wrong option");
				break;
			}

		} catch (SQLException e) {
			System.out.println("Error is" + e.getMessage());
		} finally {
			functions();
		}
		
	}
	public void createProject() throws SQLException, ParseException, IOException {
		 try {
	            Statement statement = con.createStatement();
	            sc_obj.nextLine();
	            
	            System.out.println("Enter project name:");
	            String name = sc_obj.nextLine();
	            System.out.println("Enter area:");
	            String area = sc_obj.nextLine();
	            System.out.println("Enter pincode:");
	            int pincode = sc_obj.nextInt();
	            sc_obj.nextLine();
	            System.out.println("Enter total members:");
	            int totalmembers = sc_obj.nextInt();
	            sc_obj.nextLine();
	            System.out.println("Enter estimated cost:");
	            int cost = sc_obj.nextInt();
	            sc_obj.nextLine();
	            System.out.println("Enter start date");
	            String startdate = sc_obj.nextLine();
	            System.out.println("Enter end date:");
	            String enddate = sc_obj.nextLine();

	            statement.executeUpdate("insert into Project(Project_Name,Area,Pincode,Total_members,Cost_estimated,Start_date,End_date)" +
	                    "values('" + name + "','" + area + "','" + pincode + "','" + totalmembers + "','" + cost + "','" + startdate + "','" + enddate + "')");

	            System.out.println("Done");
	            statement.close();

	        } catch (SQLException e) {
	            System.out.println("Error is:" + e.getMessage());
	        } finally {
	            functions();
	        }
		
	}
	public void deleteGpm() {
		try {
            Statement statement = con.createStatement();
            System.out.print("Enter Id to delete a GPM: ");
            int id = sc_obj.nextInt();
            statement.executeUpdate("Delete from GPM where Id = '"+id+"'");
            con.close();
            System.out.print("Done");
           
        } catch (Exception e) {
            System.out.print(e);
        }
		
	}
	public void updateGpm() throws SQLException, ParseException, IOException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Statement statement = con.createStatement();
            System.out.print("Enter GPM Email_Id: ");
            String Email = bufferedReader.readLine();
            
            ResultSet email = statement.executeQuery("select * from GPM where Email_id = '" + Email + "' ");
            
            System.out.println("What would you want to update ?\n"
            		+ "1. Name \n"
            		+ "2. Password \n"
            		+ "3. Area \n"
            		+ "4. Pincode \n");
            
            int cg = sc_obj.nextInt();
            
            switch (cg) {
            
			case 1:
				System.out.println("Enter new name:");
				String name = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Name ='" + name + "' where Email_id = '" + Email + "' ");
                con.close();
                System.out.println("Done");
				break;
				
			case 2:
				System.out.println("Enter new password:");
				String pw = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Password ='" + pw + "' where Email_id = '" + Email + "' ");
				System.out.println("Done");
                con.close();
				break;
				
			case 3:
				System.out.println("Enter to update Area");
				String area = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Area ='" + area + "' where Email_id = '" + Email + "' ");
				System.out.println("Done");
                con.close();
				break;
							
			case 4:
				System.out.println("Enter to update Pincode");
				String pin = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Pincode ='" + pin + "' where Email_id = '" + Email + "' ");
				System.out.println("Done");
                con.close();
				break;
				
			default:
				System.out.println("Wrong option");
				break;
			}

        } catch (SQLException e) {
            System.out.println("Error is" + e.getMessage());
        } finally {
            functions();
        }
	}
	
	public void createGpm() throws SQLException, ParseException, IOException {
		try {
            Statement statement = con.createStatement();
            sc_obj.nextLine();
            
            System.out.println("Enter name:");
            String name = sc_obj.nextLine();
            System.out.println("Enter email id:");
            String email_id = sc_obj.nextLine();
            System.out.println("Enter Password:");
            String password = sc_obj.nextLine();
            System.out.println("Enter Area:");
            String area = sc_obj.nextLine();
            System.out.println("Enter PinCode:");
            int pincode = sc_obj.nextInt();
            int bdo_id = 1;

            statement.execute("insert into GPM(Name,Email_id,Password,Area,Pincode,Bdo_id)" +
                    "values('" + name + "','" + email_id + "','" + password + "','" + area + "','" + pincode + "','" + bdo_id + "')");
            System.out.println("Done");
            statement.close();

        } catch (SQLException e) {
            System.out.println("Error is" + e.getMessage());
        } finally {
            functions();
        }
	}
	
	public void bdo_login() throws SQLException {
		try {
            Statement statement = con.createStatement();
            System.out.print("Enter Email_Id: ");
            String Email = sc_obj.nextLine();
            System.out.print("Enter Password: ");
            String Password = sc_obj.nextLine();
                ResultSet rs = statement.executeQuery("select * from BDO where Email_id = '" + Email + "'  AND password = '" + Password + "'");
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
