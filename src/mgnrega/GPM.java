package mgnrega;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			issue_job_card();
			break;
		case 6:
			work_allot();
			break;
		default:
			System.out.println("Default ");
		}

	}

	private void create_member() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			sc_obj.nextLine();

			System.out.println("Enter name:");
			String name = sc_obj.nextLine();
			System.out.println("Enter email:");
			String email = sc_obj.nextLine();
			System.out.println("Enter password:");
			String password = sc_obj.nextLine();
			System.out.println("Enter area:");
			String area = sc_obj.nextLine();
			System.out.println("Enter pincode:");
			int pincode = sc_obj.nextInt();
			sc_obj.nextLine();
			System.out.println("Enter age:");
			int age = sc_obj.nextInt();
			sc_obj.nextLine();
			System.out.println("Enter GPM id");
			int id = sc_obj.nextInt();

			statement.execute("insert into Member(Name,Email_id,Password,Area,Pincode,Age,Gpm_id)" +
					"values('" + name + "','" + email + "','" + password + "','" + area + "','" + pincode + "','" + age + "','" + id + "')");
			System.out.println("Done");
			statement.close();


		} catch (SQLException e) {
			System.out.println("Error is:" + e.getMessage());
		} finally {
			functions();

		}

	}

	private void assign_member() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			System.out.println("Enter Project id: ");
			int pid = sc_obj.nextInt();
			System.out.println("Enter Member id: ");
			int mid = sc_obj.nextInt();
			System.out.println("Enter Gpm id: ");
			int gid = sc_obj.nextInt();
			statement.execute("insert into Project_member(Pid,Mid,Gpm_id)" +
					"values('" + pid + "','" + mid + "','" + gid + "')");
			statement.close();
			System.out.println("Done");
			statement.execute("update Project set Is_alloted_to='true' where id='" + mid + "'");
			statement.close();

		} catch (SQLException e) {
			System.out.println("Error is: " + e.getMessage());
		} finally {
			functions();
		}

	}

	private void update_member() throws IOException, SQLException, ParseException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			Statement statement = con.createStatement();
			System.out.print("Enter Member Email_Id: ");
			String Email = bufferedReader.readLine();

			ResultSet email = statement.executeQuery("select * from Member where Email_id = '" + Email + "' ");

			System.out.println("What would you want to update ?\n"
					+ "1. Name \n"
					+ "2. Password \n"
					+ "3. Area \n"
					+ "4. Pincode \n"
					+ "5. Age");

			int cg = sc_obj.nextInt();

			switch (cg) {

			case 1:
				System.out.println("Enter new name:");
				String name = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Name ='" + name + "' where Email_id = '" + email + "' ");
				con.close();
				System.out.println("Done");
				break;

			case 2:
				System.out.println("Enter new password:");
				String pw = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Password ='" + pw + "' where Email_id = '" + email + "' ");
				System.out.println("Password has changed");
				con.close();
				break;

			case 3:
				System.out.println("Enter to update Area");
				String area = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Area ='" + area + "' where Email_id = '" + email + "' ");
				System.out.println("Done");
				con.close();
				break;

			case 4:
				System.out.println("Enter to update Pincode");
				String pin = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Pincode ='" + pin + "' where Email_id = '" + email + "' ");
				System.out.println("Done");
				con.close();
				break;
			
			case 5:
				System.out.println("Enter to update Age");
				String age = bufferedReader.readLine();
				statement.executeUpdate("Update GPM set Pincode ='" + age + "' where Email_id = '" + email + "' ");
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

	private void delete_member() {
		try {
			Statement statement = con.createStatement();
			System.out.print("Enter Id to delete a Member: ");
			int id = sc_obj.nextInt();
			statement.executeUpdate("Delete from Member where Id = '"+id+"'");
			con.close();
			System.out.print("Done");

		} catch (Exception e) {
			System.out.print(e);
		}


	}

	private void issue_job_card() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			System.out.println("Enter Member Id:");
			int mid = sc_obj.nextInt();
			ResultSet res = statement.executeQuery("select Id,Name,Email_id,Area,Pincode,Age from Member where id='" + mid + "'");
			while (res.next()) {
				System.out.println("JOB CARD\n");
				System.out.println("1.Member Id: " + res.getInt(1));
				System.out.println("2.Name: " + res.getString(2));              
				System.out.println("3.Email_id: " + res.getString(3));
				System.out.println("4.Area: " + res.getInt(5));
				System.out.println("5.Pincode: " + res.getInt(6));
				System.out.println("5.Age: " + res.getInt(7));

			}

		} catch (SQLException e) {
			System.out.println("Error is:" + e.getMessage());
		} finally {
			functions();
		}

	}

	private void work_allot() throws SQLException, ParseException, IOException {
		try {
			Statement statement = con.createStatement();
			ResultSet res = statement.executeQuery("select Id,Is_allocated_to from Project");

		} catch (SQLException e) {
			System.out.println("Error is:" + e.getMessage());
		} finally {
			functions();
		}

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
				System.out.print("WELCOME \nChoose from the below options");
				functions();
			} else
				System.out.print("EmailId/Password is incorrect");
		} catch (Exception e) {
			System.out.print(e);
		}
	}

}
