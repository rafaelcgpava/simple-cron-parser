package br.com.challange.cron.current;

import org.springframework.stereotype.Component;

@Component
public class CurrentTimeParser {
	
	public CurrentTime parse(String... _args) {
		if(_args.length == 0) {
			throw new IllegalArgumentException("No CLI parameters inputed");
		}
		
		String [] args = _args[0].split(":");
		
		if(args.length != 2) {
			throw new IllegalArgumentException("CLI parameters must be on format {d}{d}:{d}{d}");
		}
		
		int hour = parseHour(args[0]);
		int minute = parseMinute(args[1]);
		
		return CurrentTime.builder()
				.hour(hour)
				.minute(minute)
				.build();
	}
	
	private int parseHour(String _hour) {
		try {
			int hour = Integer.parseInt(_hour);
			
			if(hour < 0 || hour > 23) {
				throw new IllegalArgumentException("Hour must be between 0 and 23");
			}
			
			return hour;
		}
		catch(NumberFormatException ex) {
			throw new IllegalArgumentException("Hour must be a number", ex);
		}
	}
	
	private int parseMinute(String _minute) {
		try {
			int minute = Integer.parseInt(_minute);
			
			if(minute < 0 || minute > 59) {
				throw new IllegalArgumentException("Minute must be between 0 and 59");
			}
			
			return minute;
		}
		catch(NumberFormatException ex) {
			throw new IllegalArgumentException("Minute must be a number", ex);
		}
	}
}
