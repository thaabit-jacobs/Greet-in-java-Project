package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

class ClearCommandTest {
/*
	@Test
	void shouldClearUser() {
		DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
		Context context = new Context("clear John");
		ClearCommand cc = new ClearCommand();
		
		User u = new User("John");
		u.greet();
		
		db.addUserToDataBase(u);
		
		assertEquals(true, cc.execute(context).equalsIgnoreCase("John has been cleared"));
		
		db.clearDataBase();
	}
	
	@Test
	void shouldClearAllusersGreeted() {
		DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
		Context context = new Context("clear");
		ClearCommand cc = new ClearCommand();
		
		User u = new User("John");
		u.greet();
		
		db.addUserToDataBase(u);
		
		assertEquals(true, cc.execute(context).equalsIgnoreCase("All users have been cleared"));
		
		db.clearDataBase();
	}
*/
}
