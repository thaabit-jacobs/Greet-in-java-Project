package commands;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import user.User;

class CommandTest {

	@Test
	void shouldDisplayGreetedUsers() {
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
	void shouldReturnTheNumOfTimesUserGreeted() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(1).greet();
		users.get(1).greet();
		users.get(1).greet();
		
		assertEquals(true, com.greeted("Sarah").equals("Sarah: greeted:3"));
	}
	
	@Test
	void shouldReturnTheNumUsersThatHaveBeenGreeted() {
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
	void shouldRemoveAllGreetdUsers() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		
		com.clear();
		
		assertEquals(true, com.getUserList().get(0).getUserName().equals("Mbuyi"));
	}
	
	@Test
	void shouldRemoveUserSpecifiedByUserName() {
		ArrayList<User> users = new ArrayList<User>();
		users.add(new User("John"));
		users.add(new User("Sarah"));
		users.add(new User("Mbuyi"));
		
		Command com = new Command(users);
		users.get(0).greet();
		users.get(1).greet();
		users.get(2).greet();
		
		com.clear("Mbuyi");
		
		assertEquals(2, com.counter());
	}
	
	@Test
	void shouldGetCommandsList() {
		ArrayList<User> users = new ArrayList<User>();
		Command com = new Command(users);

		assertEquals(true, com.help().equals("Commands: \n<greet name language> \n<greeted> \n<greeted username> \n<counter> \n<clear> \n<clear username> \n<exit> \n<help>"));
	}
}
