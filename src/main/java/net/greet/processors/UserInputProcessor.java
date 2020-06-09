package net.greet.processors;

public class UserInputProcessor {
	
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
	
}
