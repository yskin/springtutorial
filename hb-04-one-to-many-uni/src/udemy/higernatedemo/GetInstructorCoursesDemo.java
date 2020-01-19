package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
        .addAnnotatedClass(Course.class).buildSessionFactory();
    // create session
    Session session = factory.getCurrentSession();
    try {
      // start a transaction
      session.beginTransaction();
      // get the instructor from database
      int id = 1;
      Instructor instructor = session.get(Instructor.class, id);
      System.out.println("Instructor: " + instructor);
      System.out.println("Courses: " + instructor.getCourses());
      // commit transaction
      session.getTransaction().commit();
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }

}
