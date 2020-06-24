package net.greet.processors.user_input;

import net.greet.exceptions.NameNotFoundException;

public class Context {
	
	private String userInput;
	
	private String command;
	
	private String name;
	
	private String language;
	
	public Context(String userInput) {
		
		this.userInput = userInput;
		
		int firstSpace = this.userInput.indexOf(" ");
		int secondSpace = this.userInput.lastIndexOf(" ");
		
		if(firstSpace == -1) {
			this.command = userInput;
			
		} else if((firstSpace != -1 && secondSpace != -1) && firstSpace == secondSpace) {
			this.command = userInput.substring(0, firstSpace);
			this.name = userInput.substring(firstSpace + 1);
			
		} else if((firstSpace != -1 && secondSpace != -1) && firstSpace != secondSpace) {
			this.command = userInput.substring(0, firstSpace);
			this.name = userInput.substring(firstSpace + 1, secondSpace);
			
			this.language = userInput.substring(secondSpace + 1);
		}
		
	}
	
	public String getCommandEntered() {
		return this.command;
	}
	
	public String getNameEntered() throws NameNotFoundException {
		if(this.name.equals(""))
			throw new NameNotFoundException("User not found");
		
		return this.name;
	}
	
	public String getLanguageEntered() {
		return language;
	}
}
