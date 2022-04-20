package br.com.challange.cron.nextexecution;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NextExecutionCalculatorResult {
	List<NextExecution> nextTimeExecutions;
}
