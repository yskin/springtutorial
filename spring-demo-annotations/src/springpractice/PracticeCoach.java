package springpractice;

import org.springframework.stereotype.Component;

@Component
public class PracticeCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "PracticeCoach.getDailyWorkout()";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}
