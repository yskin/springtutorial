package udemy.higernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import udemy.hibernatedemo.entity.Course;
import udemy.hibernatedemo.entity.Instructor;
import udemy.hibernatedemo.entity.InstructorDetail;

public class FetchJoinDemo {

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
      // Hibernate query with HQL


      // get the instructor from database
      int id = 1;
      Query<Instructor> query = session.createQuery(
          "select i from Instructor i join fetch i.courses where i.id=:instructorId",
          Instructor.class);
      // set parameter on query
      query.setParameter("instructorId", id);
      // execute query and get instructor
      Instructor instructor = query.getSingleResult();
      System.out.println("Instructor: " + instructor);
      // commit transaction
      session.getTransaction().commit();
      // close the session
      session.close();
      System.out.println("The session is now closed.");
      System.out.println("Courses: " + instructor.getCourses());
      System.out.println("Done!");
    } finally {
      session.close();
      factory.close();
    }
  }

}
