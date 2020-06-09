package net.greet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import net.greet.commands.Command;
import net.greet.processors.DataBaseCommandsProcessor;
import net.greet.processors.UserInputProcessor;
//import commands.*;
import net.greet.users.*;
/*
public class Greet {	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DataBaseCommandsProcessor dbprocess = new DataBaseCommandsProcessor(con);
		
		Scanner sc = new Scanner(System.in);
		ArrayList<User> users = dbprocess.moveDataToList();

		Command com = new Command(users);
		
		UserInputProcessor p = new UserInputProcessor(com);
		
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
*/