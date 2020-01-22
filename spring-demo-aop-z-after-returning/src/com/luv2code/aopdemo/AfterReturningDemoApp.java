package com.luv2code.aopdemo;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.luv2code.aopdemo.dao.AccountDao;

public class AfterReturningDemoApp {

  public static void main(String[] args) {
    // read spring config java class
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoConfig.class);
    // get the bean from spring container
    AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
    List<Account> accounts = accountDao.findAccounts();
    System.out.println("\nMain Program: AfterReturningDemoApp");
    System.out.println("accounts: " + accounts);
    // close the context
    context.close();
  }

}
