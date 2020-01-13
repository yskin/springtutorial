package springdemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PracticeActivityFortuneService implements FortuneService {

	private List<String> fortunes = Arrays.asList("Today is your lucky day!", "Today is your lucky day!!",
			"Today is your lucky day!!!");

	@Override
	public String getFortune() {
		return fortunes.get(getRandInt(fortunes.size()));
	}

	private int getRandInt(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

}
