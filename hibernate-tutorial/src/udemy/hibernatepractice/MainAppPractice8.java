package udemy.hibernatepractice;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatepractice.entity.Employee;

public class MainAppPractice8 {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate-practice.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		saveEmployees(factory);
	}

	private static void saveEmployees(SessionFactory factory) {

	}

}
