package net.greet.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

public class GreetCommand implements Command{

	public String execute(Context context) {
		try {
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				return "User not found";
				
		} else if(context.getLanguageEntered().equalsIgnoreCase(" ")) {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98");
			DataBaseCommandsProcessor db = new DataBaseCommandsProcessor(con);
			if(db.checkIfRecordExists(context.getNameEntered())) {
				db.updateDataBase(context.getNameEntered());
				return new User(context.getNameEntered()).greet();
				
			} else {
				db.addUserToDataBase(new User(context.getNameEntered()));
				db.updateDataBase(context.getNameEntered());
				return new User(context.getNameEntered()).greet();
			}
			
		} else {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98");
			DataBaseCommandsProcessor db = new DataBaseCommandsProcessor(con);
			
			if(db.checkIfRecordExists(context.getNameEntered())) {
				db.updateDataBase(context.getNameEntered());
				return new User(context.getNameEntered()).greet(context.getLanguageEntered());
				
			} else {
				db.addUserToDataBase(new User(context.getNameEntered()));
				db.updateDataBase(context.getNameEntered());
				return new User(context.getNameEntered()).greet(context.getLanguageEntered());
			}
		}
		} catch(NameNotFoundException e) {
			return "User not found";
		} catch(SQLException se) {
			System.out.println(se + " : Sql query issues or database");
			se.printStackTrace();
		}
		
		return "";
	}
}
	


