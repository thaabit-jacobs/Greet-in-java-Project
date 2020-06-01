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
	public void processGreet(String name, String language){
		
		if(language == "")
			System.out.println(getUser(name).greet());
		
		System.out.println(getUser(name).greet(language));
	}
	
	//greeted proccesor
	public void processGreeted(String name) {
		
		if(name == "") 
			System.out.println(com.greeted().toString());
		
		System.out.println(com.greeted(name));	
	}
	
	public void processClear(String name) {
		
		if(name == "")
			com.clear();
		
		com.clear(name);
	}
	
	public String[] processInput(String userInput) {
		String command = "";
		String name = "";
		String language = "";
		
		int spaceOne = userInput.indexOf(" ");
		int spaceTwo = userInput.lastIndexOf(" ");
		
		if(spaceOne == -1 && spaceTwo == -1) {
			command = userInput;
		} else if(spaceOne != -1 && spaceTwo == -1) {
			command = userInput.substring(0, spaceOne);
			name = userInput.substring(spaceOne + 1);
		} else if(spaceOne != -1 && spaceTwo != -1) {
			command = userInput.substring(0, spaceOne);
			name = userInput.substring(spaceOne + 1, spaceTwo);
			language = userInput.substring(spaceTwo + 1);
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
	
	public static void main(String[] args) {
		Processor p = new Processor(new Command(new ArrayList<User>()));
		
		String[] arr = p.processInput("greet Thaabit Language");
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
	}

}
