package net.greet.commands;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.user_input.Context;

public class GreetedCommand extends Command {
	
	public String execute(Context context) {
		try {
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				if(this.greeted().equalsIgnoreCase("[]")) {
					return "No users have been greeted";
				}
				
				return this.greeted();
				
			} else {
				return this.greeted(context.getNameEntered());
			}
			
		} catch (NameNotFoundException e) {
			return "User not found";
			
		}
	}
	
}
