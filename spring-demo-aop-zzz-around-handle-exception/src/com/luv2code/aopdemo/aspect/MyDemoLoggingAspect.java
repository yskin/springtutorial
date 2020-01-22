package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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

  private static Logger logger = Logger.getLogger(MyDemoLoggingAspect.class.getName());

  @Around("execution(* com.luv2code.aopdemo.service.TrafficFortuneService.getFortune(..))")
  public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    String method = proceedingJoinPoint.getSignature().toShortString();
    logger.info("\tExecuting @Around on method: " + method);
    long begin = System.currentTimeMillis();
    Object result = new Object();
    try {
      result = proceedingJoinPoint.proceed();
    } catch (Throwable e) {
      logger.warning("aroundGetFortune catch the exception: " + e.getMessage());
      // result = "Major accident! But no worries! ...";
      throw e;
    }
    long end = System.currentTimeMillis();
    long duration = end - begin;
    logger.info("\tDuration: " + duration / 1000.0 + " seconds");
    return result;
  }

  @After("execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))")
  public void afterFinallyFindAccountsAdvice(JoinPoint jointPoint) {
    String method = jointPoint.getSignature().toShortString();
    logger.info("=> Executing @After(finally) on method: " + method);
  }

  @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
      throwing = "e")
  public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable e) {
    String method = joinPoint.getSignature().toShortString();
    logger.info("\tExecuting @AfterThrowing on method: " + method);
    logger.info("\texception: " + e);
  }

  @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDao.findAccounts(..))",
      returning = "accounts")
  public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> accounts) {
    String method = joinPoint.getSignature().toShortString();
    logger.info("\tExecuting @AfterReturning on method: " + method);
    logger.info("\taccounts: " + accounts);
    for (Account account : accounts) {
      account.setName(account.getName().toUpperCase());
    }
    logger.info("\taccounts: " + accounts);
  }

  @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackage()")
  public void beforeAdvice(JoinPoint joinPoint) {
    System.out.print("=> Executing @Before advice on method: ");
    // display the method signature
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    logger.info("\tMethod: " + methodSignature);
    // display method arguments
    // get args
    Object[] args = joinPoint.getArgs();
    // loop through args
    int i = 0;
    for (Object arg : args) {
      logger.info("\targ:" + i++ + ", arg: " + arg);
    }
  }

}
