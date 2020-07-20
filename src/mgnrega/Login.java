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
		System.out.println("Welcome...Choose the appropriate option \n"
				+ "1. BDO Login \n"
				+ "2. GPM Login \n"
				+ "3. Member Login");
		
		int lc = sc_obj.nextInt();
		
		switch (lc) {
		case 1:
			System.out.println("You have selected : " + lc);
			bdo.bdo_login();
			break;
			
		case 2:
			System.out.println("You have selected : " + lc);
			gpm.gpm_login();
			break;
			
		case 3:
			System.out.println("You have selected : " + lc);
			member.member_login();
			break;

		default:
			System.out.println("Wrong option..Try again");
			break;
		}
	}
	
	
	

public static void main(String[] args) throws SQLException, ParseException, IOException {
    
	Login login = new Login();
    Db_connection db = new Db_connection();
    con = db.connect();
    login.login_choice();

}
}