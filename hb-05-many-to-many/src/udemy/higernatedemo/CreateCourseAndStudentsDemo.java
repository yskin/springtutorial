package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;
import udemy.hibernatedemo.entity.Review;
import udemy.hibernatedemo.entity.Student;

public class CreateCourseAndStudentsDemo {

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
      // create a course
      Course course = new Course("Pacman - How To Score One Million Points!");
      // save the course
      session.save(course);
      System.out.println("Saved the course: " + course);
      // create students
      Student student1 = new Student("John", "Doe", "john@gmail.com");
      Student student2 = new Student("Mary", "Public", "mary@gmail.com");
      // add students to the course
      course.add(student1);
      course.add(student2);
      // save the students
      System.out.println("Saving students...");
      session.save(student1);
      session.save(student2);
      System.out.println("Saved students: " + course.getStudents());
      // commit transaction
      session.getTransaction().commit();
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }

}
