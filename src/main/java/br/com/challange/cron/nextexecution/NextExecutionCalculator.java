package br.com.challange.cron.nextexecution;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.challange.cron.Cron;
import br.com.challange.cron.current.CurrentTime;

@Component
public class NextExecutionCalculator {
	
	public NextExecutionCalculatorResult calculate(List<Cron> crons, CurrentTime currentTime) {
		
		List<NextExecution> nextTimeExecutions = new ArrayList<>();
		
		for(Cron cron : crons) {
			String scheduledHour = cron.getHour();
			
			String scheduledMinute = cron.getMinute();
			
			if(scheduledHour.equals("*") && scheduledMinute.equals("*")) {
				NextExecution nextTime = NextExecution.builder()
						.hour(currentTime.getHour())
						.minute(currentTime.getMinute())
						.schedulerName(cron.getName())
						.when("today")
						.build();
				
				nextTimeExecutions.add(nextTime);
				
				continue;
			}
			
			if(scheduledMinute.equals("*")) {
				NextExecution nextTime = NextExecution.builder()
						.hour(Integer.parseInt(scheduledHour))
						.minute(currentTime.getHour() == Integer.parseInt(scheduledHour) ? currentTime.getMinute() : 0)
						.schedulerName(cron.getName())
						.when(currentTime.getHour() == Integer.parseInt(scheduledHour) ? "today" : "tomorrow")
						.build();
				
				nextTimeExecutions.add(nextTime);
				
				continue;
			}
			
			if(scheduledHour.equals("*")) {
				if(currentTime.getHour() == 23) {
					NextExecution nextTime = NextExecution.builder()
							.hour(currentTime.getMinute() <= Integer.parseInt(scheduledMinute) ? currentTime.getHour() : 0)
							.minute(Integer.parseInt(scheduledMinute))
							.schedulerName(cron.getName())
							.when(currentTime.getMinute() <= Integer.parseInt(scheduledMinute) ? "today" : "tomorow")
							.build();
					
					nextTimeExecutions.add(nextTime);
					
					continue;
				}
				else {
					NextExecution nextTime = NextExecution.builder()
							.hour(currentTime.getMinute() <= Integer.parseInt(scheduledMinute) ? currentTime.getHour() : currentTime.getHour() + 1)
							.minute(Integer.parseInt(cron.getMinute()))
							.schedulerName(cron.getName())
							.when("today")
							.build();
					
					nextTimeExecutions.add(nextTime);
					
					continue;
				}
			}
			
			if(!cron.getHour().equals("*") && !cron.getMinute().equals("*")) {
				
				if(Integer.parseInt(scheduledHour) >= currentTime.getHour()) {
					NextExecution nextTime = NextExecution.builder()
							.hour(Integer.parseInt(scheduledHour))
							.minute(Integer.parseInt(scheduledMinute))
							.schedulerName(cron.getName())
							.when("today")
							.build();
							
					nextTimeExecutions.add(nextTime);
					
					continue;
				}
				else {
					NextExecution nextTime = NextExecution.builder()
							.hour(Integer.parseInt(scheduledHour))
							.minute(Integer.parseInt(scheduledMinute))
							.schedulerName(cron.getName())
							.when("tomorrow")
							.build();
							
					nextTimeExecutions.add(nextTime);
					
					continue;
				}						
			}
		}
		
		return NextExecutionCalculatorResult.builder()
				.nextTimeExecutions(nextTimeExecutions)
				.build();
	}
	
}