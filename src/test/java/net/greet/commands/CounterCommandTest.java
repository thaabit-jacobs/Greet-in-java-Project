package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

class CounterCommandTest {
	private final String jdbcURL = "jdbc:postgresql://localhost:5432/greeter";
	private Connection connection;
	private final DataBaseCommandsProcessor db;
	
	CounterCommandTest() throws SQLException{
		
		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/greeter", "postgres", "1234");
		db = new DataBaseCommandsProcessor(connection);
	}

	@Test
	void shouldReturnCountUsersGreeted() {
		Context context = new Context("counter");
		CounterCommand cc = new CounterCommand();
		User john = new User("Jonh");
		User sarah = new User("Sarah");
		
		john.greet();
		sarah.greet();
		
		assertEquals(true, cc.execute(context).equalsIgnoreCase("The number of unique user(s) greeted: 0"));
		
		db.clearDataBase();
	}
}


