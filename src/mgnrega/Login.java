package mgnrega;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Login {
	public static Connection con;
	Scanner sc_obj = new Scanner(System.in);
	BDO bdo = new BDO();
	GPM gpm = new GPM();
	Member member = new Member();
	
	public void login_choice() throws SQLException, ParseException, IOException {
		/*This function enables user to choose among the options to login*/
		
		System.out.println("Welcome to MGNREGA...Choose the appropriate option \n"
				+ "1. BDO Login \n"
				+ "2. GPM Login \n"
				+ "3. Member Login \n"
				+ "4. Exit");
		
		int lc = sc_obj.nextInt();
		
		switch (lc) {
		case 1:
			bdo.bdo_login();
			break;
			
		case 2:
			gpm.gpm_login();
			break;
			
		case 3:
			member.member_login();
			break;
		
		case 4:
			System.out.println("Thank you");
			break;

		default:
			System.out.println("Wrong option..Try again \n");
			login_choice();
			break;
		}
	}
	
public static void main(String[] args) throws SQLException, ParseException, IOException {
    /*Main function*/
	
	Login login = new Login();
    Db_connection db = new Db_connection();
    con = db.connect();
    login.login_choice();
}
}