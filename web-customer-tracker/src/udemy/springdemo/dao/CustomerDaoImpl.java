package udemy.springdemo.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import udemy.springdemo.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

  // need to inject the Hibernate session factory
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Customer> getCustomers() {
    // get the current Hibernate session
    Session session = sessionFactory.getCurrentSession();
    // create a query ... sort by last name
    Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);
    // execute query and get result list
    List<Customer> customers = query.getResultList();
    // return the result
    return customers;
  }

  @Override
  public Customer getCustomer(int id) {
    return sessionFactory.getCurrentSession().get(Customer.class, id);
  }

  @Override
  public void saveCustomer(Customer customer) {
    sessionFactory.getCurrentSession().saveOrUpdate(customer);
  }

  @Override
  public void deleteCustomer(int id) {
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("delete from Customer where id=:id");
    query.setParameter("id", id);
    query.executeUpdate();
  }

}
