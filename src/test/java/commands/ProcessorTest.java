package commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import user.User;

class ProcessorTest {
	
	@Test
	void shouldGetUser() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Processor p = new Processor(new Command(ul));
		
		assertEquals(true, p.getUser("Kauthar").getUserName().equals("Kauthar"));
	}
	
	@Test
	void shouldGreetUserWithLanguage() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Processor p = new Processor(new Command(ul));
		p.processGreet("Thaabit", "Afrikaans");
		
		assertEquals(1, p.getUser("Thaabit").getGreetCount());
	}
	
	@Test
	void shouldGreetUserWithDefaultLanguage() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Processor p = new Processor(new Command(ul));
		p.processGreet("Thaabit", "");
		
		assertEquals(1, p.getUser("Thaabit").getGreetCount());
	}
	
	@Test
	void shouldReturnNumberOfTimesUserIsGreeted() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.get(0).greet();
		
		Processor p = new Processor(new Command(ul));
		p.processGreeted("Thaabit");
		
		assertEquals(1, p.getUser("Thaabit").getGreetCount());
	}
	
	@Test
	void shouldClearAllUsersGreeted() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		
		Processor p = new Processor(new Command(ul));
		p.processClear("");
		
		assertEquals(0, p.getCommand().counter());
	}
	
	@Test
	void shouldClearUserGreetCount() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.get(0).greet();
		
		Processor p = new Processor(new Command(ul));
		p.processClear("Thaabit");
		
		assertEquals(0, ul.get(0).getGreetCount());
	}
	
	@Test
	void shouldReturnUserCommand() {
		ArrayList<User> ul = new ArrayList<User>();
		Processor p = new Processor(new Command(ul));
		
		String [] arr = p.processInput("help");
		
		assertEquals(true, arr[0].equals("help"));
	}
	
	@Test
	void shouldReturnUserName() {
		ArrayList<User> ul = new ArrayList<User>();
		Processor p = new Processor(new Command(ul));
		
		String [] arr = p.processInput("clear Thaabit");
		
		assertEquals(true, arr[1].equals("Thaabit"));
	}
	
	@Test
	void shouldReturnUserLanguage() {
		ArrayList<User> ul = new ArrayList<User>();
		Processor p = new Processor(new Command(ul));
		
		String [] arr = p.processInput("greet Thaabit English");
		
		assertEquals(true, arr[2].equals("English"));
	}
}
