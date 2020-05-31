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
		
		assertEquals(true, p.processGreet("Thaabit", "Afrikaans").equals("Hallo Thaabit"));
	}
	
	@Test
	void shouldGreetUserInDefaultLanguage() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.add(new User("Kauthar"));
		Processor p = new Processor(new Command(ul));
		
		assertEquals(true, p.processGreet("Thaabit", "").equals("Hello Thaabit"));
	}
	
	@Test
	void shouldReturnNumberOfTimesUserIsGreeted() {
		ArrayList<User> ul = new ArrayList<User>();
		ul.add(new User("Thaabit"));
		ul.get(0).greet();
		
		Processor p = new Processor(new Command(ul));
		
		assertEquals(true, p.processGreeted("Thaabit").equals("Thaabit: greeted:1"));
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
}
