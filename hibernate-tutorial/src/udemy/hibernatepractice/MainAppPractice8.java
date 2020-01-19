package udemy.hibernatepractice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatepractice.entity.Employee;

public class MainAppPractice8 {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("hibernate-practice.cfg.xml")
				.addAnnotatedClass(Employee.class).buildSessionFactory();
		try {
			// save
			Employee employee = saveEmployees(factory, "name1", "name2", "aaa");
			saveEmployees(factory, "name11", "name22", "bbb");
			// retrieve by key
			employee = queryEmployees(factory, employee);
			System.out.println(employee);
			// query by company
			List<Employee> employees = queryEmployeesByComapny(factory, "aaa");
			for (Employee e : employees) {
				System.out.println(e);
			}
			// delete
			deleteEmployees(factory, employees);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}

	private static void deleteEmployees(SessionFactory factory, List<Employee> employees) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		for (Employee e : employees) {
			session.createQuery("delete from Employee e where e.id=" + e.getId()).executeUpdate();
		}
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	private static List<Employee> queryEmployeesByComapny(SessionFactory factory, String company) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Employee> e = session.createQuery("from Employee e where e.company='" + company + "'").getResultList();
		session.getTransaction().commit();
		return e;
	}

	private static Employee queryEmployees(SessionFactory factory, Employee employee) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("target id: " + employee.getId());
		Employee e = session.get(Employee.class, employee.getId());
		session.getTransaction().commit();
		return e;
	}

	private static Employee saveEmployees(SessionFactory factory, String firstName, String lastName, String company) {
		Employee e = new Employee(firstName, lastName, company);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		return e;
	}

}
