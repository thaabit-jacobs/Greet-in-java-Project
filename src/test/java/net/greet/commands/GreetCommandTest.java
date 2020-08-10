package net.greet.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.processors.user_input.Context;

class GreetCommandTest {
	@Test
	void shouldExecuteGreetCommandWithDefaultLanguage() {
		Context context = new Context("greet John");
		GreetCommand gc = new GreetCommand();
		
		assertEquals(true, gc.execute(context).equalsIgnoreCase("Good day John"));
	}
	
	@Test
	void shouldExecuteGreetCommandWithLanguage() {
		Context context = new Context("greet John Afrikaans");
		GreetCommand gc = new GreetCommand();
		
		assertEquals(true, gc.execute(context).equalsIgnoreCase("Goie dag John"));
	}
	
	@Test
	void shouldReturnUserNotFound() {
		Context context = new Context("greet ");
		GreetCommand gc = new GreetCommand();
		
		assertEquals(true, gc.execute(context).equalsIgnoreCase("User not found"));
	}
}
