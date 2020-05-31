package user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void shouldGetUserName() {
		User u = new User("Thaabit");
		assertEquals(true, u.getUserName().equals("Thaabit"));
	}
	
	@Test
	void defaultGreet() {
		User u = new User("Thaabit");
		assertEquals(true, u.greet().equals("Hello Thaabit"));
	}
	
	@Test
	void greetInEnglish() {
		User u = new User("Thaabit");
		assertEquals(true, u.greet("English").equals("Hello Thaabit"));
	}
	
	@Test
	void greetInAfrikaans() {
		User u = new User("Thaabit");
		assertEquals(true, u.greet("Afrikaans").equals("Hallo Thaabit"));
	}
	
	@Test
	void greetInZulu() {
		User u = new User("Thaabit");
		assertEquals(true, u.greet("Zulu").equals("Sawubona Thaabit"));
	}
	
	@Test
	void shouldGetGreetCount() {
		User u = new User("Thaabit");
		u.greet();
		u.greet("Zulu");
		assertEquals(2, u.getGreetCount());
	}
	
	@Test
	void shouldSetGreetCountToZero() {
		User u = new User("Thaabit");
		u.greet();
		u.setGreetCount();
		assertEquals(0, u.getGreetCount());
	}

}
