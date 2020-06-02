package commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import user.User;

class CommandTest {

	@Test
	void shouldDisplayGreetedUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John", 0));
		users.add(new User("Sarah", 0));
		users.add(new User("Mbuyi", 0));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(2).greet();
		assertEquals(true, com.greeted().get(1).getUserName().equalsIgnoreCase("Mbuyi"));
	}
	
	@Test
	void shouldReturnTheNumOfTimesUserGreeted() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John", 0));
		users.add(new User("Sarah", 0));
		users.add(new User("Mbuyi", 0));
		
		Command com = new Command(users);
		users.get(1).greet();
		users.get(1).greet();
		users.get(1).greet();
		
		assertEquals(true, com.greeted("Sarah").equals("Number of times Sarah was greeted: 3"));
	}
	
	@Test
	void shouldReturnTheNumUsersThatHaveBeenGreeted() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John", 0));
		users.add(new User("Sarah", 0));
		users.add(new User("Mbuyi", 0));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		
		assertEquals(2, com.counter());
	}
	
	
	@Test
	void shouldSetCounterToZero() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John", 0));
		users.add(new User("Sarah", 0));
		users.add(new User("Mbuyi", 0));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		
		com.clear();
		
		assertEquals(0, com.getUserList().get(0).getGreetCount());
	}
	
	@Test
	void shouldRemoveUserSpecifiedByUserName() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John", 0));
		users.add(new User("Sarah", 0));
		users.add(new User("Mbuyi", 0));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		users.get(2).greet();
		
		com.clear("Mbuyi");
		
		assertEquals(2, com.counter());
	}
	
}
