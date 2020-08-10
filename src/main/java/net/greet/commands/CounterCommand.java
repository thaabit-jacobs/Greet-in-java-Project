package net.greet.commands;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;

public class CounterCommand implements Command {
	
	public String execute(Context context) {
		String greetedUsers = "";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98");
			DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(con);
			greetedUsers = "The number of unique user(s) greeted: " + dbcp.countGreetedUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return greetedUsers;
	}

}
