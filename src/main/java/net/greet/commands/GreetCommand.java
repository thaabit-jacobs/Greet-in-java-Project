package net.greet.commands;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

public class GreetCommand extends Command{
	
	public String execute(Context context) {
		try {
			if(context.getNameEntered() == null) {
				return "Must greet a user";
				
		} else if(context.getLanguageEntered() == null) {
			return new User(context.getNameEntered()).greet();
			
		} else {
			return new User(context.getNameEntered()).greet(context.getLanguageEntered());
		}
		} catch(NameNotFoundException e) {
			return "User not found";
		}
	}
}
	


