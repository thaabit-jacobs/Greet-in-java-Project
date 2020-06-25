package net.greet.commands;

import net.greet.processors.user_input.Context;

public class HelpCommand implements Command{
	
	public String execute(Context context) {
		return "\ngreet   [username] [language] \ngreeted \ngreeted [username] \ncounter \nclear \nclear   [username] \n* \nhelp \nexit";
	}

}
