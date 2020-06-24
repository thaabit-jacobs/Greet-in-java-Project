package net.greet;

import java.util.*;

import net.greet.commands.*;
import net.greet.exceptions.CommandNotFoundException;
import net.greet.processors.user_input.Context;

public class App {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<String, Command> commandMap = new HashMap<>();
		commandMap.put("greet", new GreetCommand());
		commandMap.put("greeted", new GreetedCommand());
		commandMap.put("clear", new ClearCommand());
		commandMap.put("counter", new CounterCommand());
		commandMap.put("help", new HelpCommand());
		commandMap.put("*", new LanguageCommand());
		
		while(true) {
			System.out.print("\n");
			System.out.print("Enter command: ");
			String userInput = sc.nextLine();
			
			if(userInput.equalsIgnoreCase("exit"))
				return;
			
			try {
				Context context = new Context(userInput);
				Command command = commandMap.get(context.getCommandEntered());
				String result = command.execute(context);
				System.out.println(result);
				
			} catch(CommandNotFoundException ce) {
				System.out.println("Command not found");
			}

		}
	}
}
