package br.com.challange.cron;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cron {
	String hour;
	
	String minute;
	
	String name;
}
