package udemy.higernatedemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// start a transaction
			session.beginTransaction();
			// query students
			List<Student> students = quertyStudents(session, "from Student");
			// display the students
			displayStudents(students);
			// query students: lastName='Doe'
			students = quertyStudents(session, "from Student s where s.lastName='Doe'");
			System.out.println("--- Students who have last name of Doe ---");
			displayStudents(students);
			// query students lastName='Doe' or firstName='Daffy'
			students = quertyStudents(session, "from Student s where s.lastName='Doe' or s.firstName='Daffy'");
			System.out.println("--- Students who have last name of Doe or first name of Daffy ---");
			displayStudents(students);
			// querty students where email LIKE 'hibernatedemo.com'
			students = quertyStudents(session, "from Student s where s.email like '%hibernatedemo.com'");
			System.out.println("--- Students whose email ends with hibernatedemo.com ---");
			displayStudents(students);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	@SuppressWarnings("unchecked")
	private static List<Student> quertyStudents(Session session, String hql) {
		return session.createQuery(hql).getResultList();
	}

	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}

}
