package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.processors.database_processors.DataBaseCommandsProcessor;
import net.greet.processors.user_input.Context;
import net.greet.users.User;

class CounterCommandTest {

	@Test
	void shouldReturnCountUsersGreeted() {
		DataBaseCommandsProcessor db = new DataBaseCommandsProcessor();
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


