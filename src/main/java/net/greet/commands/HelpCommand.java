package net.greet.commands;

import net.greet.processors.user_input.Context;

public class HelpCommand extends Command{
	
	public String execute(Context context) {
		return this.help();
	}

}
