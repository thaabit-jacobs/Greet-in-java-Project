package net.greet.processors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.exceptions.NameNotFoundException;

class ContextTest {

	@Test
	void shouldReturnTheCommandInUserInput() {
		try {
			Context context = new Context("clear");
			assertEquals(true, context.getCommand().equals("clear"));
			
		} catch(NameNotFoundException nfe) {
			System.out.print("User name not found");
		}
	}
	
	@Test
	void shouldReturnTheUserNameInUserInput() {
		try {
			Context context = new Context("greeted Thaabit");
			assertEquals(true, context.getName().equals("Thaabit"));
			
		} catch(NameNotFoundException nfe) {
			System.out.print("User name not found");
		}
	}
	
	@Test
	void shouldReturnTheLanguageInUserInput() {
		try {
			Context context = new Context("greet Thaabit Afrikaans");
			assertEquals(true, context.getLanguage().equals("Afrikaans"));
			
		} catch(NameNotFoundException nfe) {
			System.out.print("User name not found");
		}
	}
}
