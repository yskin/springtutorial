package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;

public class CreateDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
    // create session
    Session session = factory.getCurrentSession();
    try {
      // create the objects
      // Instructor instructor = new Instructor("Chad", "Darby", "darby@gmail.com");
      // InstructorDetail instructorDetail = new InstructorDetail("https://youtube.com/darby",
      // "Cording");

      Instructor instructor = new Instructor("Madhu", "Patel", "patel@gmail.com");
      InstructorDetail instructorDetail =
          new InstructorDetail("https://youtube.com/patel", "Guitar");

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
      factory.close();
    }
  }

}
