package springpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FirstCoach implements Coach {

	@Autowired
	@Qualifier("fileRandomFortuneService")
	private FortuneService fortuneService;

	@Override
	public String getDailyWorkout() {
		return "PracticeCoach.getDailyWorkout()";
	}

	@Override
	public String getFortune() {
		return fortuneService.getFortune();
	}

}
