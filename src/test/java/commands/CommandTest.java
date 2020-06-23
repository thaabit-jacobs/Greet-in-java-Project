package commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import net.greet.commands.Command;
import net.greet.users.*;

class CommandTest {

	@Test
	void shouldReturnListOfGreetedUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		
		Command com = new Command(users);
		
		users.get(0).greet();

		assertEquals(true, com.greeted().equalsIgnoreCase("[John has been greeted 1 time(s)]"));
	}
	
	void shouldReturnTheUserGreetCount() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		
		Command com = new Command(users);
		
		users.get(0).greet();

		assertEquals(true, com.greeted("Mbuyi").equalsIgnoreCase("Mbuyi has been greeted 1 time(s)"));
	}
	
	
	@Test
	void shouldDisplayTheNumOfTimesUserGreeted() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(1).greet();
		users.get(1).greet();
		users.get(1).greet();
		
		assertEquals(true, com.greeted("Sarah").equals("Sarah has been greeted 3 time(s)"));
	}
	
	@Test
	void shouldReturnTheCountOfUsersGreeted() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		
		assertEquals(true, com.counter().equalsIgnoreCase("The number of unique user(s) greeted: 2" ));
	}
	
	
	@Test
	void shouldClearAllUsersGreeted() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		
		assertEquals(true, com.clear().equalsIgnoreCase("All users have been cleared"));
	}
	
	@Test
	void shouldRemoveUserGreetCount() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		
		assertEquals(true, com.clear("Mbuyi").equalsIgnoreCase("Mbuyi has been cleared"));
	}
	
}
