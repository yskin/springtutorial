package springpractice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		testClassPathXmlContext();

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContext.class);
		Coach coach = ctx.getBean("coach7", Coach.class);
		System.out.println(coach.getDailyWorkout());
		System.out.println(coach.getFortune());
		ctx.close();
	}

	private static void testClassPathXmlContext() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("practice-applicationContext.xml");
		Coach theCoach = context.getBean("practiceCoach", Coach.class);
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getFortune());
		context.close();
	}

}
