package net.greet.commands;

import java.util.*;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.users.*;

public class Command {
	
	private DataBaseCommandsProcessor dbcp;
	
	public Command() {
		
		dbcp = new DataBaseCommandsProcessor();
		
	}
	
	public String greeted() {
		return dbcp.queryGreetedUsers().toString();
		
	}
	
	public String greeted(String userName) {
		return dbcp.queryGreetedUser(userName);
		
	}
	
	public String counter() {
		return "The number of unique user(s) greeted: " + dbcp.countGreetedUsers();
		
	}
	
	public String clear() {
		this.dbcp.deleteGreetedRecordsFromDataBase();
		
		return "All users have been cleared";
	}
	
	public String clear(String userName) {
		this.dbcp.clearUserDataBase(userName);
		
		return userName + " has been cleared";
	}
	
	public String help() {
		return "\ngreet   [username] [language] \ngreeted \ngreeted [username] \ncounter \nclear \nclear   [username] \n* \nhelp \nexit";
	}
	
	public String languages() {
		return "\nlanguages \n========= \nENGLISH \nAFRIKAANS \nXHOSA \nZULU \nSPANISH \nJAPANESE \nARABIC \nHINDI \nFRENCH \nRUSSIAN";
	}
}
