package greetings_run;

import java.util.*;

import commands.Command;

import user.User;

public class Greeting {
	
	public static User getUser(Command c, String name) {
		User user = new User("");
		
		for(User u: c.getUserList()) {
			if(u.getUserName().equalsIgnoreCase(name))
				user = u;
		}
		
		return user;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(new User("Thaabit"));
		users.add(new User("Jon"));
		users.add(new User("Mike"));
		users.add(new User("Ty"));
		users.add(new User("Aiko"));
		
		Command com = new Command(users);
		
		
		while(true) {
			System.out.println("\nEnter command: ");
			System.out.println("*************");
			
			String command = sc.nextLine();
			
			if(command.equals("greet")) {
				System.out.print("Enter name: ");
				String name = sc.nextLine();
				System.out.print("Enter language: ");
				String language = sc.nextLine();
				
				if(language.equals(""))
					System.out.println(getUser(com, name).greet());
				else	
					System.out.println(getUser(com, name).greet(language));
				
			} else if(command.equals("greeted")) {
				System.out.print("Enter name: ");
				String name = sc.nextLine();
				
				if(name.equals(""))
					System.out.println(com.greeted());
				else
					System.out.println(getUser(com, name).getGreetCount());
				
			} else if(command.equals("counter"))
				System.out.println(com.counter());
			
			  else if(command.equals("clear")) {
				System.out.print("Enter name: ");
				String name = sc.nextLine();
				
				if(name.equals(""))
					com.clear();
				else
					com.clear(name);
				
			} else if(command.equals("exit"))
				return;
			  
			  else if(command.equals("help"))
				  System.out.println(com.help());
			  
			  else
				  System.out.println("Invalid command");
		}
	}
	
}
