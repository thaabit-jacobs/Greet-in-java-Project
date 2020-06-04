package user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void shouldGetUserName() {
		User u = new User("Thaabit", 0);
		assertEquals(true, u.getUserName().equals("Thaabit"));
	}
	
	@Test
	void defaultGreetingShouldBeEnglish() {
		User u = new User("Thaabit", 0);
		assertEquals(true, u.greet().equals("Good day Thaabit"));
	}
	
	@Test
	void shouldGreetInEnglish() {
		User u = new User("Thaabit", 0);
		assertEquals(true, u.greet("English").equals("Good day Thaabit"));
	}
	
	@Test
	void shouldgreetInAfrikaans() {
		User u = new User("Thaabit", 0);
		assertEquals(true, u.greet("Afrikaans").equals("Goie dag Thaabit"));
	}
	
	@Test
	void shouldgreetInZulu() {
		User u = new User("Thaabit", 0);
		assertEquals(true, u.greet("Zulu").equals("Usuku oluhle Thaabit"));
	}
	
	@Test
	void shouldGetGreetCount() {
		User u = new User("Thaabit", 0);
		u.greet();
		u.greet("Zulu");
		assertEquals(2, u.getGreetCount());
	}
	
	@Test
	void shouldSetGreetCountToZero() {
		User u = new User("Thaabit", 0);
		u.greet();
		u.setGreetCount();
		assertEquals(0, u.getGreetCount());
	}

}
