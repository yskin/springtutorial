package springpractice;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileRandomFortuneService implements FortuneService {

	@Value("${sport.fortunes.one}")
	private String fortune1;

	@Value("${sport.fortunes.two}")
	private String fortune2;

	@Value("${sport.fortunes.three}")
	private String fortune3;

	List<String> fortunes;

	@PostConstruct
	private void setupFortunes() {
		fortunes = Arrays.asList(fortune1, fortune2, fortune3);
	}

	@Override
	public String getFortune() {
		return fortunes.get(new Random().nextInt(fortunes.size()));
	}

}
