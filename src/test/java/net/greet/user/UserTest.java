package net.greet.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import net.greet.users.*;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void shouldGetUserName() {
		User u = new User("Thaabit");
		assertEquals(true, u.getUserName().equals("Thaabit"));
	}
	
	@Test
	void shouldGreetUserInEnglish() {
		User u = new User("Thaabit");
		String greetingUser = u.greet();
		assertEquals(true, greetingUser.equals("Good day Thaabit"));
	}
	
	@Test
	void shouldGreetUserInXhosa() {
		User u = new User("Thaabit");
		String greetingUser = u.greet("xhosa");
		System.out.println(greetingUser);
		assertEquals(true, greetingUser.equalsIgnoreCase("Mholweni emini nje Thaabit"));
	}
	
	@Test
	void shouldGetGreetCount() {
		User u = new User("Thaabit");
		u.greet();
		assertEquals(1, u.getGreetCount());
	}
	
	@Test
	void shouldSetGreetCountToZero() {
		User u = new User("Thaabit");
		u.greet();
		u.setGreetCount();
		assertEquals(0, u.getGreetCount());
	}

}
