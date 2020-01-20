package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;
import udemy.hibernatedemo.entity.Review;
import udemy.hibernatedemo.entity.Student;

public class AddCoursesForMaryDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
    // create session
    Session session = factory.getCurrentSession();
    try {
      // start a transaction
      session.beginTransaction();
      // get the student Mary from database
      int id = 2;
      Student mary = session.get(Student.class, id);
      System.out.println("Loaded Student(Mary): " + mary);
      System.out.println("Loaded Student(Mary) Courses: " + mary.getCourses());
      // crate more courses
      Course course1 = new Course("Rubik's Cube - How to Speed Cube");
      Course course2 = new Course("Atari 2600 - Game Development");
      // add Mary to the courses
      course1.add(mary);
      course2.add(mary);
      // save the courses
      System.out.println("Saving the courses...");
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
