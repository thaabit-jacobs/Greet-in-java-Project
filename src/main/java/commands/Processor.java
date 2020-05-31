package commands;

import java.util.ArrayList;

import user.User;

public class Processor {
	
	private Command com;
	
	public Processor(Command com) {
		
		this.com = com;
		
	}
	//get access to a specific user
	public User getUser(String name) {
		User user = new User("");
		
		for(User u: com.getUserList()) {
			if(u.getUserName().equalsIgnoreCase(name))
				user = u;
		}
		
		return user;
	}
	
	//greet processor
	public String processGreet(String name, String language){
		
		if(language == "")
			return getUser(name).greet();
		
		return getUser(name).greet(language);
	}
	
	//greeted proccesor
	public String processGreeted(String name) {
		
		if(name == "") 
			return com.greeted().toString();
		
		return com.greeted(name);	
	}
	
	public void processClear(String name) {
		
		if(name == "")
			com.clear();
		
		com.clear(name);
	}
	
	//processor method to call the appropriate processor method depending on the command
	public void processCommand(String userCommand) {
		String command = userCommand.substring(0, userCommand.indexOf(" "));
		String name = userCommand.substring(userCommand.indexOf(" ") + 1);
		String language;
		
		if((command.length() + name.length() + 1) != userCommand.length()) {
			name = userCommand.substring(userCommand.indexOf(" ") + 1, userCommand.lastIndexOf(" "));
			language = userCommand.substring(userCommand.lastIndexOf(" ") + 1);
		} else
			language = "";	
		
		switch(userCommand) {
		case "help":
			com.help();
			break;
		case "counter":
			com.counter();
			break;
		case "greet":
			processGreet(name, language);
			break;
		case "greeted":
			processGreeted(name);
			break;
		case "clear":
			processClear(name);
			break;
		default:
			System.out.println("invalid command");
		}
	}
	
	public Command getCommand() {
		return com;
	}

}
