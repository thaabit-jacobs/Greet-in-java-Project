package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

class GreetedCommandTest {
	private final String jdbcURL = "jdbc:postgresql://localhost:5432/greeter";
	private Connection connection;
	private final DataBaseCommandsProcessor db;
	
	GreetedCommandTest() throws SQLException{
		
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "1234");
		db = new DataBaseCommandsProcessor(connection);
	}

	@Test
	void shouldReturnListOfGreetedUsrs() {
		Context context = new Context("greeted");
		GreetedCommand gc = new GreetedCommand();
		db.clearDataBase();
		
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
		db.clearDataBase();
		
		User john = new User("John");
		
		john.greet();
		
		db.addUserToDataBase(john);
		
		assertEquals(true, gc.execute(context).equalsIgnoreCase("John has been greeted 1 time(s)"));
		
		db.clearDataBase();
	}
}
