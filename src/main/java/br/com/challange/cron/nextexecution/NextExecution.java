package br.com.challange.cron.nextexecution;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NextExecution {
	int hour;
	int minute;
	String when;
	String schedulerName;
}
