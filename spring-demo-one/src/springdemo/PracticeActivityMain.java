package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PracticeActivityMain {

	public static void main(String[] args) {
		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"practiceActivity-applicationContext.xml");
		// retrieve bean from spring container
		Coach theCoach = context.getBean("practiceActivityCoach", Coach.class);
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		// bean scope
		testBeanScope(context);
		// close the context
		context.close();
	}

	private static void testBeanScope(ClassPathXmlApplicationContext context) {
		Coach theCoach = context.getBean("practiceActivityCoach", Coach.class);
		Coach alphaCoach = context.getBean("practiceActivityCoach", Coach.class);
		boolean result = (theCoach == alphaCoach);
		System.out.println("testBeanScope isEqual?: " + result);
		System.out.println("theCoach: " + theCoach);
		System.out.println("alphaCoach: " + alphaCoach);
	}

}
