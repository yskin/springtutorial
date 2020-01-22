package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.luv2code.aopdemo.Account;

@Component
public class AccountDao {

  private String name;
  private String serviceCode;

  public void addAccount(Account account, boolean vip) {
    System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
  }

  public List<Account> findAccounts(boolean tripWire) {
    // for academic purpose ... simulate an exception
    if (tripWire) {
      throw new RuntimeException("No soup for you!!!");
    }
    List<Account> accounts = new ArrayList<>();
    accounts.add(new Account("John", "Silver"));
    accounts.add(new Account("Madhu", "Platinum"));
    accounts.add(new Account("Luca", "Gold"));
    System.out.println("called: com.luv2code.aopdemo.dao.AccountDao.findAccounts()");
    return accounts;
  }

  public boolean doWork() {
    System.out.println(getClass() + ": doWork()");
    return true;
  }

  public String getName() {
    System.out.println(getClass() + ": getName()");
    return name;
  }

  public void setName(String name) {
    System.out.println(getClass() + ": setName()");
    this.name = name;
  }

  public String getServiceCode() {
    System.out.println(getClass() + ": getServiceCode()");
    return serviceCode;
  }

  public void setServiceCode(String serviceCode) {
    System.out.println(getClass() + ": setServiceCode()");
    this.serviceCode = serviceCode;
  }

}
