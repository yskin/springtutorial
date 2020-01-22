package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

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
      System.out.println("\targ:" + i++ + ", arg: "+arg);
    }
  }

}
