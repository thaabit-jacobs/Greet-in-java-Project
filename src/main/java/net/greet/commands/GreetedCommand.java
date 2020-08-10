package net.greet.commands;

import java.sql.DriverManager;
import java.sql.SQLException;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;

public class GreetedCommand implements Command {
	
	public String execute(Context context) {
		try {
			DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "1234"));
			
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				if(dbcp.getAllGreetedUsers().toString().equalsIgnoreCase("[]")) {
					return "No users have been greeted";
				}
				
				return dbcp.getAllGreetedUsers().toString();
				
			} else {
				return dbcp.queryGreetedUser(context.getNameEntered());
			}
			
		} catch (NameNotFoundException e) {
			return "User not found";
		} catch (SQLException e) {
			System.out.println(e + " : Sql query issues or database");
			e.printStackTrace();
		}
		
		return " ";
	}
	
}
