package net.greet.commands;

import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.user_input.Context;

public class ClearCommand extends Command {
	
	public String execute(Context context) {
		try {
			if(context.getNameEntered().equalsIgnoreCase(" ")) {
				return this.clear();
				
			} else {
				return this.clear(context.getNameEntered());
			}
			
		} catch(NameNotFoundException e) {
			return "User not found";
		}
	}
}
