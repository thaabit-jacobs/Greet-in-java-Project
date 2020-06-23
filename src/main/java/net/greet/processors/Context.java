package net.greet.processors;

import net.greet.exceptions.NameNotFoundException;

public class Context {
	
	private String userInput;
	
	private String command;
	
	private String name;
	
	private String language;
	
	public Context(String userInput) throws NameNotFoundException {
		
		this.userInput = userInput;
		
		int firstSpace = this.userInput.indexOf(" ");
		int secondSpace = this.userInput.lastIndexOf(" ");
		
		if(firstSpace == -1) {
			this.command = this.userInput;
			
		} else if(firstSpace != -1 && (firstSpace == secondSpace)) {
			this.command = this.userInput.substring(0, firstSpace);
			this.name = this.userInput.substring(firstSpace + 1);
			
			if(this.name.equals(""))
				throw new NameNotFoundException("User name not found");
			
			this.name = this.name.substring(0, 1).toUpperCase() + this.name.substring(1).toLowerCase();
			
		} else {
			this.command = this.userInput.substring(0, firstSpace);
			this.name = this.userInput.substring(firstSpace + 1, secondSpace);		
			this.name = this.name.substring(0, 1).toUpperCase() + this.name.substring(1).toLowerCase();
			this.language = this.userInput.substring(secondSpace + 1);
		}
		
	}
	
	public String getCommand() {
		return command;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLanguage() {
		return language;
	}

}
