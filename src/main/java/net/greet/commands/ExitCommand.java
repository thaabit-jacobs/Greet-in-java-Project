package net.greet.commands;

import net.greet.processors.user_input.Context;

public class ExitCommand implements Command{
	
	public String execute(Context context) {
		return "exiting...";
	}
}
