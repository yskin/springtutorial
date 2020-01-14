package springpractice;

public class Coach7 implements Coach {

	private FortuneService fortuneService;

	public Coach7(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Called - Coach7.getDailyWorkout()";
	}

	@Override
	public String getFortune() {
		return fortuneService.getFortune();
	}

}
