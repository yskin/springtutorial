package com.luv2code.aopdemo.aspect;

import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

  @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
      throwing = "e")
  public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable e) {
    String method = joinPoint.getSignature().toShortString();
    System.out.println("\tExecuting @AfterThrowing on method: " + method);
    System.out.println("\texception: " + e);
  }

  @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
      returning = "accounts")
  public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {
    String method = joinPoint.getSignature().toShortString();
    System.out.println("\tExecuting @AfterReturning on method: " + method);
    System.out.println("\taccounts: " + accounts);
    for (Account account : accounts) {
      account.setName(account.getName().toUpperCase());
    }
    System.out.println("\taccounts: " + accounts);
  }

  @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackage()")
  public void beforeAdvice(JoinPoint joinPoint) {
    System.out.print("=> Executing @Before advice on method: ");
    // display the method signature
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    System.out.println();
    System.out.println("\tMethod: " + methodSignature);
    // display method arguments
    // get args
    Object[] args = joinPoint.getArgs();
    // loop through args
    int i = 0;
    for (Object arg : args) {
      System.out.println("\targ:" + i++ + ", arg: " + arg);
    }
  }

}
