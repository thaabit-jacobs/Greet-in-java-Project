package greeter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.greet.greeter.Greet;
import net.greet.processors.CommandsProcessor;

public class GreetTest {
	
	@Test
	void shouldReturnCommandProcessorType() {
		Greet g = new Greet();
		assertEquals(true, g.createCommandProcessor() instanceof CommandsProcessor);
	}
	
	@Test
	void shouldReturnAppsText() {
		Greet g = new Greet();
		assertEquals(true, g.appText().equalsIgnoreCase("Greet-in-java" + "\n============="));
	}
}
