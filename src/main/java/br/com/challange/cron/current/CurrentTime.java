package br.com.challange.cron.current;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CurrentTime {
	int hour;
	
	int minute;
}
