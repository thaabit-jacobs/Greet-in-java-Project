package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.commands.Command;
import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.users.*;

class CommandTest {

	@Test
	void shouldReturnListOfGreetedUsers() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
		
		Command com = new Command();

		assertEquals(true, com.greeted().equalsIgnoreCase("[John has been greeted 1 time(s)]"));
		
		dbcp.clearDataBase();
	}
	
	@Test
	void shouldReturnTheUserGreeted() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
		
		Command com = new Command();

		assertEquals(true, com.greeted("John").equalsIgnoreCase("John has been greeted 1 time(s)"));
		
		dbcp.clearDataBase();
	}
	
	@Test
	void shouldReturnTheCountOfUsersGreeted() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		User sarah = new User("Sarah");
		sarah.greet();
		
		dbcp.addUserToDataBase(john);
		dbcp.addUserToDataBase(sarah);
		
		Command com = new Command();

		assertEquals(true, com.counter().equalsIgnoreCase("The number of unique net.greet.user(s) greeted: 2"));
		
		dbcp.clearDataBase();
	}
	
	@Test
	void shouldClearAllUsersGreeted() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		
		User john = new User("John");
		john.greet();
		
		User sarah = new User("Sarah");
		sarah.greet();
		
		dbcp.addUserToDataBase(john);
		dbcp.addUserToDataBase(sarah);
		
		Command com = new Command();
		
		dbcp.clearDataBase();
		
		assertEquals(true, com.counter().equalsIgnoreCase("The number of unique net.greet.user(s) greeted: 0"));
	}
	
	@Test
	void shouldClearGreetedUsersGreeted() {
		DataBaseCommandsProcessor dbcp = new DataBaseCommandsProcessor();
		dbcp.clearDataBase();
		
		User john = new User("John");
		john.greet();
		
		dbcp.addUserToDataBase(john);
		
		Command com = new Command();
		
		dbcp.clearUserDataBase("John");
		
		assertEquals(true, com.counter().equalsIgnoreCase("The number of unique net.greet.user(s) greeted: 0"));
		
		dbcp.clearDataBase();
	}
	
	
	
}
