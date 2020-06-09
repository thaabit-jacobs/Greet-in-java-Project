package net.greet.greeter;

import java.util.*;

import net.greet.commands.Command;
import net.greet.processors.CommandsProcessor;
import net.greet.processors.DataBaseCommandsProcessor;

import net.greet.users.*;

public class Greet {	

	public CommandsProcessor createCommandProcessor() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor(); 
		ArrayList<User> userList = dbcp.moveDataToList();
		Command com = new Command(userList);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		return cp;
	}
	
	public String appText() {
		return "Greet-in-java" + "\n=============";
	}
	
	public void runMainAppLoop() {
		Scanner sc = new Scanner(System.in);
		
		String ui = "";
		
		while(!ui.equalsIgnoreCase("exit")) {
			System.out.println(" ");
			System.out.print("Enter command: ");
				
			ui = sc.nextLine();
			
			createCommandProcessor().processCommand(ui);
		}
		
		sc.close();
	}
	
}
