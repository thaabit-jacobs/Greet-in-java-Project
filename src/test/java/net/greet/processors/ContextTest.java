package net.greet.processors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.exceptions.CommandNotFoundException;
import net.greet.exceptions.NameNotFoundException;
import net.greet.processors.user_input.Context;

class ContextTest {

	@Test
	void shouldReturnTheCommandInUserInput() throws NameNotFoundException {
		Context context;
		context = new Context("clear");
		try {
			assertEquals(true, context.getCommandEntered().equals("clear"));
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void shouldReturnTheUserNameInUserInput() throws NameNotFoundException {
		Context context = new Context("greeted Thaabit");
		assertEquals(true, context.getNameEntered().equals("Thaabit"));
			
	}
	
	@Test
	void shouldReturnTheLanguageInUserInput() throws NameNotFoundException {
		Context context = new Context("greet Thaabit Afrikaans");
		assertEquals(true, context.getLanguageEntered().equals("afrikaans"));
	}
}
