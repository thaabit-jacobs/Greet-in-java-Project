package net.greet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import commands.*;
import db.processes.DbProcess;
import user.User;

public class Greet {	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		final String jdbcURL = "jdbc:h2:file:./target/user_db";
	
		Connection con = DriverManager.getConnection(jdbcURL, "sa", "");
		
		DbProcess dbprocess = new DbProcess(con);
		
		Scanner sc = new Scanner(System.in);
		ArrayList<User> users = dbprocess.moveDataToList();

		Command com = new Command(users);
		Processor p = new Processor(com);
		
		MAIN:
		while(true) {
			System.out.print("\nEnter command: ");
			
			String ui = sc.nextLine();
			
			if(ui.equals("exit"))
				 break MAIN; 
			
			p.processCommand(ui);
		}
		
		dbprocess.moveDataToDataDase(users);
		
		sc.close();
		dbprocess.getConnection().close();
	}
}
