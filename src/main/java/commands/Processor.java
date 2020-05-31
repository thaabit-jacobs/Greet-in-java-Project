package commands;

import user.User;

public class Processor {
	
	Command com;
	
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
	
	//processor method to call the appropriate processor method depending on the command
	public void processCommand(String userCom) {
		String command = userCom.substring(0, userCom.indexOf(" "));
		String name = userCom.substring(userCom.indexOf(" ") + 1);
		String language;
		
		if((command.length() + name.length() + 1) != userCom.length()) {
			name = userCom.substring(userCom.indexOf(" ") + 1, userCom.lastIndexOf(" "));
			language = userCom.substring(userCom.lastIndexOf(" ") + 1);
		} else
			language = "";		
		
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
	
}
