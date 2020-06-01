package greetings_run;

import java.util.*;

import commands.*;

import user.User;

public class Greeting {	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<User> users = new ArrayList<User>();
		
		users.add(new User("Thaabit"));
		users.add(new User("Jon"));
		users.add(new User("Mike"));
		users.add(new User("Ty"));
		users.add(new User("Aiko"));
		
		Command com = new Command(users);
		Processor p = new Processor(com);
		
		while(true) {
			System.out.print("\nEnter command: ");
			
			String ui = sc.nextLine();
			
			if(ui.equals("exit"))
				return;
			
			p.processCommand(ui);
		}
	}
}
