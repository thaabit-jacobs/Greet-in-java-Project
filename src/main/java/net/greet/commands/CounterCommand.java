package net.greet.commands;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;

public class CounterCommand implements Command {
	
	public String execute(Context context) {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		return "The number of unique user(s) greeted: " + dbcp.countGreetedUsers();
	}

}
