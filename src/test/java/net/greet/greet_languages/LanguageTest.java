package net.greet.greet_languages;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.greetings_languages.*;

public class LanguageTest {
	
	@Test
	void shouldReturnGreetingForLanguage() {
		String greeting = Language.ENGLISH.getGreeting();
		assertEquals(true, greeting.equals("Good day "));
	}
}
