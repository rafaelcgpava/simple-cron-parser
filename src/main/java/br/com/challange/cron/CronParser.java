package br.com.challange.cron;

import org.springframework.stereotype.Component;

@Component
public class CronParser {
	
	public Cron parse(String _line){
		String [] line = _line.split(" ");
		
		return Cron.builder()
				.minute(line[0])
				.hour(line[1])
				.name(line[2])
				.build();
	}
	
}
