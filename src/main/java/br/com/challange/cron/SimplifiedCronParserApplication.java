package br.com.challange.cron;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.challange.cron.current.CurrentTime;
import br.com.challange.cron.current.CurrentTimeParser;
import br.com.challange.cron.nextexecution.NextExecution;
import br.com.challange.cron.nextexecution.NextExecutionCalculator;
import br.com.challange.cron.nextexecution.NextExecutionCalculatorResult;

@SpringBootApplication
public class SimplifiedCronParserApplication implements CommandLineRunner  {
	
	CurrentTimeParser currentTimeParser;
	CronParser cronParser;
	NextExecutionCalculator nextExecutionCalculator;
	
	public SimplifiedCronParserApplication(CurrentTimeParser currentTimeParser,
											CronParser cronParser,
										    NextExecutionCalculator nextExecutionCalculator) {
		this.currentTimeParser = currentTimeParser;
		this.cronParser = cronParser;
		this.nextExecutionCalculator = nextExecutionCalculator;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimplifiedCronParserApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Cron> crons = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()))) {
			String line = reader.readLine();

			while (line != null) {
				
				if(!line.isBlank()) {
					Cron cron = cronParser.parse(line);

					crons.add(cron);
				}
				
				line = reader.readLine();
			}
		}
		
		CurrentTime currentTime = currentTimeParser.parse(args);
		
		NextExecutionCalculatorResult result = nextExecutionCalculator.calculate(crons, currentTime);
		
		for(NextExecution nextTime : result.getNextTimeExecutions()) {
			System.out.println(nextTime.getHour() + ":" + nextTime.getMinute() + " " + nextTime.getWhen() + " - " + nextTime.getSchedulerName());
		}
	}

}
