package commands;

import java.util.ArrayList;

import user.User;

public class Processor {
	
	private Command com;
	
	public Processor(Command com) {
		
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
	
	public void processGreet(String name, String language){
		
		if(language == "")
			System.out.println(getUser(name).greet());
		else
			System.out.println(getUser(name).greet(language));
	}
	
	public void processGreeted(String name) {
		
		if(name == "") 
			System.out.println(com.greeted().toString());
		else
			System.out.println(com.greeted(name));	
	}
	
	public void processClear(String name) {
		
		if(name == "")
			com.clear();
		else
			com.clear(name);
	}
	
	public String[] processInput(String userInput) {
		String command = "";
		String name = "";
		String language = "";
		
		int firstSpace = userInput.indexOf(" ");
		int secondSpace = userInput.lastIndexOf(" ");
		
		if(firstSpace == -1) {
			command = userInput;
		} else if(firstSpace != -1 && (firstSpace == secondSpace)) {
			command = userInput.substring(0, firstSpace);
			name = userInput.substring(firstSpace + 1);
		} else {
			command = userInput.substring(0, firstSpace);
			name = userInput.substring(firstSpace + 1, secondSpace);
			language = userInput.substring(secondSpace + 1);
		}
		
		String[] arr = {command, name, language};
		
		return arr;
	}
	
	//processor method to call the appropriate processor method depending on the command
	public void processCommand(String userCommand) {
		
		String[] arr = processInput(userCommand);
		
		if(arr[0].equals("greet"))
			processGreet(arr[1], arr[2]);
		else if(arr[0].equals("greeted"))
			processGreeted(arr[1]);
		else if(arr[0].equals("counter"))
			System.out.println("number of unique users greeted: " + com.counter());
		else if(arr[0].equals("clear"))
			processClear(arr[1]);
		else if(arr[0].equals("help"))
			System.out.println(com.help());
		else
			System.out.println("Invalid command"); 

	}
	
	public Command getCommand() {
		return com;
	}

}
