package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;
import udemy.hibernatedemo.entity.Review;

public class CreateCourseAndReviewsDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
        .addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();
    // create session
    Session session = factory.getCurrentSession();
    try {
      // start a transaction
      session.beginTransaction();
      // create a course
      Course course = new Course("Pacman - How To Score One Million Points!");
      // add some reviews
      course.add(new Review("Great course ... loved it!"));
      course.add(new Review("Cool course, job well done!"));
      course.add(new Review("What a dumb course, you are an idiot!"));
      // save the course ... and leverage the cascade all
      System.out.println("Saving the course");
      System.out.println(course);
      System.out.println(course.getReviews());
      session.save(course);
      // commit transaction
      session.getTransaction().commit();
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }

}
