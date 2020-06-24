package net.greet.processors.user_input;

import net.greet.exceptions.CommandNotFoundException;
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
			this.name = " ";
			this.language = " ";
			
		} else if((firstSpace != -1 && secondSpace != -1) && firstSpace == secondSpace) {
			this.command = userInput.substring(0, firstSpace);
			this.name = userInput.substring(firstSpace + 1);
			this.language = " ";
			
		} else if((firstSpace != -1 && secondSpace != -1) && firstSpace != secondSpace) {
			this.command = userInput.substring(0, firstSpace);
			this.name = userInput.substring(firstSpace + 1, secondSpace);
			this.language = userInput.substring(secondSpace + 1);
		}
		
	}
	
	public String getCommandEntered() throws CommandNotFoundException {
		if(!this.command.equalsIgnoreCase("*") && this.command.length() < 4)
			throw new CommandNotFoundException();
		
		return this.command.toLowerCase();
	}
	
	public String getNameEntered() throws NameNotFoundException {
		if(this.name.equals(""))
			throw new NameNotFoundException();
		
		return this.name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}
	
	public String getLanguageEntered() {
		return language.toLowerCase();
	}
}
