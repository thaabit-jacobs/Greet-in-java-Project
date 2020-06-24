package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

class GreetedCommandTest {

	@Test
	void shouldReturnListOfGreetedUsrs() {
		Context context = new Context("greeted");
		GreetedCommand gc = new GreetedCommand();
		DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
		User john = new User("John");
		
		john.greet();
		
		db.addUserToDataBase(john);
		
		assertEquals(true, gc.execute(context).equalsIgnoreCase("[John has been greeted 1 time(s)]"));
		
		db.clearDataBase();
	}
	
	@Test
	void shouldReturnGreetedUsrs() {
		Context context = new Context("greeted John");
		GreetedCommand gc = new GreetedCommand();
		DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
		User john = new User("John");
		
		john.greet();
		
		db.addUserToDataBase(john);
		
		assertEquals(true, gc.execute(context).equalsIgnoreCase("John has been greeted 1 time(s)"));
		
		db.clearDataBase();
	}

}
