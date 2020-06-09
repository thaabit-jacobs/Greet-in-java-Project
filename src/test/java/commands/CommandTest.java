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
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(2).greet();
		assertEquals(true, com.greeted().get(1).getUserName().equalsIgnoreCase("Mbuyi"));
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
		
		assertEquals(2, com.counter());
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
		
		com.clear();
		
		assertEquals(0, com.getUserList().get(0).getGreetCount());
	}
	
	@Test
	void shouldRemoveUserGreetCount() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		
		com.clear("Mbuyi");
		
		assertEquals(0, users.get(0).getGreetCount());
	}
	
}
