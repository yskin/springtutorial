package springpractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

	@Bean
	public FortuneService fortuneService7() {
		return new FortuneService7();
	}

	@Bean
	public Coach7 coach7() {
		return new Coach7(fortuneService7());
	}

}
