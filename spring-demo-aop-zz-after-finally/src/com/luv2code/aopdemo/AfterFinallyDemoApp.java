package com.luv2code.aopdemo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.luv2code.aopdemo.dao.AccountDao;

public class AfterFinallyDemoApp {

  public static void main(String[] args) {
    // read spring config java class
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoConfig.class);
    // get the bean from spring container
    AccountDao accountDao = context.getBean("accountDao", AccountDao.class);
    List<Account> accounts = new ArrayList<Account>();
    try {
      boolean tripWire = false;
      accounts = accountDao.findAccounts(tripWire);
    } catch (Exception e) {
      System.out.println("\nMain Program: caught exception: " + e);
    }
    System.out.println("\nMain Program: AfterThrowingDemoApp");
    System.out.println("accounts: " + accounts);
    // close the context
    context.close();
  }

}
