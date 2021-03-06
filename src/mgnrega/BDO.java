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
		System.out.print("\nChoose an appropriate option. \n"
				+ "1.Create Gpm \n"
				+ "2.Update Gpm \n"
				+ "3.Delete Gpm \n"
				+ "4.Create Project \n"
				+ "5.Update Project \n"
				+ "6.Delete Project\n"
				+ "7. Exit");

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

		case 7:
			System.out.println("Thank you");
			break;

		default:
			System.out.println("Wrong option...Try again \n");
			functions();
		}
	}
	
	public void deleteProject() {
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
	
	public void updateProject() throws IOException, SQLException, ParseException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			Statement statement = con.createStatement();
			
			System.out.print("Enter Project Id: ");
			String id = bufferedReader.readLine();

			ResultSet rs = statement.executeQuery("select * from Project where Id = '" + id + "' ");
			
			if (rs.next()) {
                System.out.println("\n1.Project Name: " + rs.getString(2));
                System.out.println("\n2.Area: " + rs.getString(3));
                System.out.println("\n3.Total_Members: " + rs.getInt(5));
                System.out.println("\n4.Cost_estimated: " + rs.getFloat(6));
                System.out.println("\n5.Start_date: " + rs.getDate(7));
                System.out.println("\n6.End_date: " + rs.getDate(8));

			System.out.println("What would you want to update ?\n"
					+ "1. Area \n"
					+ "2. Total Members \n"
					+ "3. Cost estimated \n");

			int up = sc_obj.nextInt();

			switch (up) {
			
			case 1:
				System.out.println("Enter Area");
				String alloc = bufferedReader.readLine();
				statement.executeUpdate("Update Project set Area ='" + alloc + "' where Id = '" + id + "' ");
			    System.out.println("Done");
				break;

			case 2:
				System.out.println("Enter Total members:");
				String tot = bufferedReader.readLine();
				statement.executeUpdate("Update Project set Total_members ='" + tot + "' where Id = '" + id + "' ");
				System.out.println("Done");
				break;

			case 3:
				System.out.println("Enter Cost estimated:");
				String ce = bufferedReader.readLine();
				statement.executeUpdate("Update Project set Cost_estimated ='" + ce + "' where Id = '" + id + "' ");
				System.out.println("Cost estimated has changed");
				break;

			default:
				System.out.println("Wrong option");
				break;
			}
			} else
				System.out.print("Incorrect email/not present");

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
			System.out.println("Enter start date in the form DD/MM/yyyy");
			String startdate = sc_obj.nextLine();
			java.util.Date sdate = new java.util.Date(startdate);
            java.sql.Date conv_sdate = new java.sql.Date(sdate.getTime());
            
			System.out.println("Enter end date in the form DD/MM/yyyy:");
			String enddate = sc_obj.nextLine();
			java.util.Date edate = new java.util.Date(enddate);
            java.sql.Date conv_edate = new java.sql.Date(edate.getTime());

			statement.executeUpdate("insert into Project(Project_Name,Area,Pincode,Total_members,Cost_estimated,Start_date,End_date)" +
					"values('" + name + "','" + area + "','" + pincode + "','" + totalmembers + "','" + cost + "','" + conv_sdate + "','" + conv_edate + "')");

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

			ResultSet rs = statement.executeQuery("select * from GPM where Email_id = '" + Email + "' ");
			if (rs.next()) {
				System.out.print("1.Name: " + rs.getString(2));
				System.out.print("\n2.Password: " + rs.getString(4));
				System.out.print("\n3.Area: " + rs.getString(5));
				System.out.print("\n4.Pincode: " + rs.getInt(6));

				System.out.println("\nWhat would you want to update ?\n"
						+ "1. Name \n"
						+ "2. Password \n"
						+ "3. Area \n"
						+ "4. Pincode \n");

				int ug = sc_obj.nextInt();

				switch (ug) {

				case 1:
					System.out.println("Enter new name:");
					String name = bufferedReader.readLine();
					statement.executeUpdate("Update GPM set Name ='" + name + "' where Email_id = '" + Email + "' ");
					System.out.println("Done");
					break;

				case 2:
					System.out.println("Enter new password:");
					String pw = bufferedReader.readLine();
					statement.executeUpdate("Update GPM set Password ='" + pw + "' where Email_id = '" + Email + "' ");
					System.out.println("Done");
					break;

				case 3:
					System.out.println("Enter to update Area");
					String area = bufferedReader.readLine();
					statement.executeUpdate("Update GPM set Area ='" + area + "' where Email_id = '" + Email + "' ");
					System.out.println("Done");
					break;

				case 4:
					System.out.println("Enter to update Pincode");
					String pin = bufferedReader.readLine();
					statement.executeUpdate("Update GPM set Pincode ='" + pin + "' where Email_id = '" + Email + "' ");
					System.out.println("Done");
					break;

				default:
					System.out.println("Wrong option");
					break;
				}
			} else
				System.out.print("Incorrect email/not present");

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
			System.out.println("Enter BDO id:");
			int bdo_id = sc_obj.nextInt();

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

	public void bdo_login() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			System.out.print("Enter Email_Id: ");
			String Email = sc_obj.nextLine();
			System.out.print("Enter Password: ");
			String Password = sc_obj.nextLine();
			ResultSet rs = statement.executeQuery("select * from BDO where Email_id = '" + Email + "'  AND password = '" + Password + "'");
			if (rs.next()) {
				System.out.print("\nWelcome...Block Development Officer \n");
				functions();
			} else
				System.out.print("EmailId/Password is incorrect");
		} catch (Exception e) {
			System.out.print(e);
		} 
	}
}
