package net.greet.commands;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;

public class ClearCommand implements Command {
	
	public String execute(Context context) {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		
		try {
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				if(dbcp.countGreetedUsers() == 0)
					return "No users have been greeted";
				
				dbcp.deleteGreetedRecordsFromDataBase();
				return "All users have been cleared";
				
			} else {
				if(dbcp.checkIfRecordExists(context.getNameEntered())) {
					if(dbcp.getUserGreetCount(context.getNameEntered()) == 0)
						return context.getNameEntered() + " has not been greeted";
					
					dbcp.clearUserDataBase(context.getNameEntered());
					return context.getNameEntered() + " has been cleared";
				}
			}
			
		} catch(NameNotFoundException e) {
			return "User not found";
		}
		
		return "No Users have been greeted";
	}
}
