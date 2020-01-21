package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;
import com.luv2code.aopdemo.Account;

@Component
public class AccountDao {

  public void addAccount(Account account, boolean vip) {
    System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
  }

  public boolean doWork() {
    System.out.println(getClass() + ": doWork()");
    return true;
  }

}
