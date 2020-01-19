package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
      // create some courses
      Course course1 = new Course("Air Guitar - The Ultimate Guide");
      Course course2 = new Course("The Pinball Masterclass");
      // add courses to instructor
      instructor.add(course1);
      instructor.add(course2);
      // save the courses
      session.save(course1);
      session.save(course2);
      // commit transaction
      session.getTransaction().commit();
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }

}
