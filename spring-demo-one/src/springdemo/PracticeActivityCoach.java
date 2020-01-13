package springdemo;

public class PracticeActivityCoach implements Coach {

	private FortuneService fortuneService;

	public PracticeActivityCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice Activity! - getDailyWorkout";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
