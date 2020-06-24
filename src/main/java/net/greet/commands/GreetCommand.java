package net.greet.commands;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

public class GreetCommand extends Command{

	public String execute(Context context) {
		try {
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				return "Must greet a user";
				
		} else if(context.getLanguageEntered().equalsIgnoreCase(" ")) {
			DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
			
			if(db.checkIfRecordExists(context.getNameEntered())) {
				db.updateDataBase(context.getNameEntered());
				return new User(context.getNameEntered()).greet();
				
			} else {
				db.addUserToDataBase(new User(context.getNameEntered()));
				db.updateDataBase(context.getNameEntered());
				return new User(context.getNameEntered()).greet();
			}
			
		} else {
			DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
			
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
		}
	}
}
	


