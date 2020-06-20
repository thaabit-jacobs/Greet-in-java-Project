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
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		} else {
			command = userInput.substring(0, firstSpace);
			name = userInput.substring(firstSpace + 1, secondSpace);
			name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			language = userInput.substring(secondSpace + 1);
		}
		
		String[] arr = {command, name, language};
		
		return arr;
	}

}
