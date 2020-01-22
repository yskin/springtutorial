package udemy.springdemo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import udemy.springdemo.entity.Customer;

@Service
public interface CustomerService {

  public List<Customer> getCustomers();

  public Customer getCustomer(int id);

  public void saveCustomer(Customer customer);

  public void deleteCustomer(int id);

}
