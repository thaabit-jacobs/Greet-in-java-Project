package net.greet.commands;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;

public class GreetedCommand implements Command {
	
	public String execute(Context context) {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		
		try {
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				if(dbcp.queryGreetedUsers().toString().equalsIgnoreCase("[]")) {
					return "No users have been greeted";
				}
				
				return dbcp.queryGreetedUsers().toString();
				
			} else {
				return dbcp.queryGreetedUser(context.getNameEntered());
			}
			
		} catch (NameNotFoundException e) {
			return "User not found";
		}
	}
	
}
