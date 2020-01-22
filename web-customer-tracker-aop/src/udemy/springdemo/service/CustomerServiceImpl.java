package udemy.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import udemy.springdemo.dao.CustomerDao;
import udemy.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

  // need to inject Customer DAO
  @Autowired // @Repository で修飾したクラスから探してくれる
  private CustomerDao customerDao;

  @Override
  @Transactional
  public List<Customer> getCustomers() {
    return customerDao.getCustomers();
  }

  @Override
  @Transactional
  public Customer getCustomer(int id) {
    return customerDao.getCustomer(id);
  }

  @Override
  @Transactional
  public void saveCustomer(Customer customer) {
    customerDao.saveCustomer(customer);
  }

  @Override
  @Transactional
  public void deleteCustomer(int id) {
    customerDao.deleteCustomer(id);
  }

}
