package net.greet.processors;

import java.sql.SQLException;

import net.greet.commands.Command;

import net.greet.users.User;

public class CommandsProcessor {
	
	private Command com;
	
	public CommandsProcessor(Command com) {
		
		this.com = com;
		
	}
	
	public User getUser(String name) {
		User user = new User("");
		
		for(User u: com.getUserList()) {
			if(u.getUserName().equalsIgnoreCase(name))
				user = u;
		}
		
		return user;
	}
	
	public String processGreet(String name, String language) throws SQLException, ClassNotFoundException{
		DataBaseCommandsProcessor dcp = new DataBaseCommandsProcessor();
		
		if(name == "")
			return "Must enter name to greet user";
		
		for(User u: com.getUserList()) {
			if(u.getUserName().equalsIgnoreCase(name)) {
				if(language == "")
					return getUser(name).greet();
				else
					return getUser(name).greet(language);
			} 
		}
		
		User user = new User(name);
		dcp.addUserToDataBase(user);
		com.getUserList().add(user);
		
		if(language == "")
			return getUser(name).greet();
		else
			return getUser(name).greet(language);
	}
	
	public String processGreeted(String name) {
		
		if(com.counter() == 0) {
			return "No users have been greeted";
		} else if(name == "") {
			for(User u: com.greeted())
				System.out.println(u);
		} else
			return com.greeted(name);
		
		return "";
	}
	
	public void processClear(String name) throws ClassNotFoundException, SQLException {
		
		if(name == "")
			com.clear();
		else
			com.clear(name);
	}
	
	public void processCommand(String userCommand) throws ClassNotFoundException, SQLException {
		UserInputProcessor userinputprocess = new UserInputProcessor();
		String[] arr = userinputprocess.processInput(userCommand);
		
		if(arr[0].equalsIgnoreCase("greet"))
			processGreet(arr[1], arr[2]);
		else if(arr[0].equalsIgnoreCase("greeted"))
			processGreeted(arr[1]);
		else if(arr[0].equalsIgnoreCase("counter"))
			System.out.println("number of unique users greeted: " + com.counter());
		else if(arr[0].equalsIgnoreCase("clear"))
			processClear(arr[1]);
		else if(arr[0].equalsIgnoreCase("help"))
			System.out.println(com.help());
		else
			System.out.println("Invalid command"); 

	}
	
	public Command getCommand() {
		return com;
	}
}
