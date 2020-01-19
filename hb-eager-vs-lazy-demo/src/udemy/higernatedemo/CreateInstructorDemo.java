package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;

public class CreateInstructorDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
        .addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
        .addAnnotatedClass(Course.class).buildSessionFactory();
    // create session
    Session session = factory.getCurrentSession();
    try {
      // create the objects
      Instructor instructor = new Instructor("Susan", "Public", "susan.public@gmail.com");
      InstructorDetail instructorDetail =
          new InstructorDetail("https://youtube.com/susan", "Video Games");
      // associate the objects
      instructor.setInstructorDetail(instructorDetail);
      // start a transaction
      session.beginTransaction();
      // save the student object
      // this will also save the detail object because of CascadeType.ALL
      System.out.println("Saving instructor: " + instructor);
      session.save(instructor);
      // commit transaction
      session.getTransaction().commit();
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }

}
