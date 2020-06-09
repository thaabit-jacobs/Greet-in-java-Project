package processors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import net.greet.processors.UserInputProcessor;

class UserInputProcessorTest {

	
	@Test
	void shouldReturnCommandHelp() {
		UserInputProcessor p = new UserInputProcessor();
		
		String [] arr = p.processInput("greet Thaabit English");
		
		assertEquals(true, arr[0].equals("greet"));
	}
	
	@Test
	void shouldReturnUserName() {
		UserInputProcessor p = new UserInputProcessor();
		
		String [] arr = p.processInput("greet Thaabit English");
		
		assertEquals(true, arr[1].equals("Thaabit"));
	}
	
	@Test
	void shouldReturnUserLanguage() {
		UserInputProcessor p = new UserInputProcessor();
		
		String [] arr = p.processInput("greet Thaabit English");
		
		assertEquals(true, arr[2].equals("English"));
	}
	
}
