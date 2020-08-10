package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

class ClearCommandTest {
	private final String jdbcURL = "jdbc:postgresql://localhost:5432/greeter";
	private Connection connection;
	private final DataBaseCommandsProcessor db;
	
	ClearCommandTest() throws SQLException{
		
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "Password98");
		db = new DataBaseCommandsProcessor(connection);
	}

	@Test
	void shouldClearUser() {
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
		Context context = new Context("clear");
		ClearCommand cc = new ClearCommand();
		
		User u = new User("John");
		u.greet();
		
		db.addUserToDataBase(u);
		
		assertEquals(true, cc.execute(context).equalsIgnoreCase("All users have been cleared"));
		
		db.clearDataBase();
	}

}
