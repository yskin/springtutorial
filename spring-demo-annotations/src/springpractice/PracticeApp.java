package springpractice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeApp {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("practice-applicationContext.xml");
		Coach theCoach = context.getBean("practiceCoach", Coach.class);
		System.out.println(theCoach.getDailyWorkout());
		context.close();
	}

}
