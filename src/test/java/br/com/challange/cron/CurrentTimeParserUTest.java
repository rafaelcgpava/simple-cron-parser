package br.com.challange.cron;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.challange.cron.current.CurrentTime;
import br.com.challange.cron.current.CurrentTimeParser;

class CurrentTimeParserUTest {
	
	private final CurrentTimeParser parser = new CurrentTimeParser();

	@Test
	void parseSuccessfully() {
		String [] args = new String[1];
		
		args[0] = "16:10";
		
		CurrentTime expected = CurrentTime.builder()
				.hour(16)
				.minute(10)
				.build();
		
		CurrentTime result = parser.parse(args);
		
		assertEquals(expected.getHour(), result.getHour());
		assertEquals(expected.getMinute(), result.getMinute());
	}
	
	@Test
	void throwsExceptionWhenHourIsNotANumber() {
		String [] args = new String[1];
		
		args[0] = "ab:10";
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
	}
	
	@Test
	void throwsExceptionWhenHourIsGreaterThan23() {
		String [] args = new String[1];
		
		args[0] = "24:10";
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
	}
	
	@Test
	void throwsExceptionWhenHourIsMinorThan1() {
		String [] args = new String[1];
		
		args[0] = "-1:10";
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
	}
	
	@Test
	void throwsExceptionWhenMinuteIsNotANumber() {
		String [] args = new String[1];
		
		args[0] = "16:ab";
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
	}
	
	@Test
	void throwsExceptionWhenMinuteIsGreaterThan59() {
		String [] args = new String[1];
		
		args[0] = "16:60";
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
	}
	
	@Test
	void throwsExceptionWhenMinuteIsMinorThan1() {
		String [] args = new String[1];
		
		args[0] = "16:-1";
		
		assertThrows(IllegalArgumentException.class, () -> parser.parse(args));
	}

}
