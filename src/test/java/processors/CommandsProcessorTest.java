package processors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import net.greet.commands.Command;

import net.greet.processors.CommandsProcessor;

import net.greet.users.User;

public class CommandsProcessorTest {
	
	
	@Test
	void shouldGetAUserByName() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		assertEquals(true, cp.getUser("Kauthar").getUserName().equals("Kauthar"));
	}
	
	@Test
	void shouldReturnErrorWhenNameGiven() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		assertEquals(true, cp.processGreet("", "").equalsIgnoreCase("Must enter name to greet user"));
	}
	
	@Test
	void shouldGreetUserWithDefaultLanguage() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		assertEquals(true, cp.processGreet("Thaabit", "").equalsIgnoreCase("Good day Thaabit"));
	}
	
	@Test
	void shouldGreetUserWithSpecifiedLanguage() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);

		
		assertEquals(true, cp.processGreet("Thaabit", "japanese").equalsIgnoreCase("Yoi tsuitachi Thaabit"));
	}
	
	@Test
	void shouldReturnErrorMessageIfNoUsersWereGreeted() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		assertEquals(true, cp.processGreeted("Thaabit").equals("No users have been greeted"));
	}
	
	@Test
	void shouldReturnTheNumberOfTimesUserGreeted() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		cp.processGreet("Thaabit", "");
		
		assertEquals(true, cp.processGreeted("Thaabit").equals("Thaabit has been greeted 1 time(s)"));
	}
	
	@Test
	void shouldClearAllUsersGreeted() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		cp.processGreet("Thaabit", "");
		cp.processClear("");

		assertEquals(0, com.getUserList().get(0).getGreetCount());
	}
	
	@Test
	void shouldClearUserGreetCount() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Command com = new Command(ul);
		CommandsProcessor cp = new CommandsProcessor(com);
		
		cp.processGreet("Thaabit", "");
		cp.processGreet("Kauthar", "");
		cp.processClear("Thaabit");
		
		assertEquals(0, ul.get(0).getGreetCount());
	}
}
