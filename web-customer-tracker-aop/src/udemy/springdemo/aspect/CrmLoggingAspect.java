package udemy.springdemo.aspect;

import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrmLoggingAspect {

  private Logger logger = Logger.getLogger(CrmLoggingAspect.class.getName());

  @Pointcut("execution(* udemy.springdemo.controller.*.*(..))")
  private void forControllerPackage() {}

  @Pointcut("execution(* udemy.springdemo.service.*.*(..))")
  private void forServicePackage() {}

  @Pointcut("execution(* udemy.springdemo.dao.*.*(..))")
  private void forDaoPackage() {}

  @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
  private void forAppFlow() {}

  @Before("forAppFlow()")
  public void before(JoinPoint joinPoint) {
    String method = joinPoint.getSignature().toShortString();
    logger.info("===>> in @Before calling from method: " + method);
    for (Object arg : joinPoint.getArgs()) {
      logger.info("===>> arg: " + arg);
    }
  }

  @AfterReturning(pointcut = "forAppFlow()", returning = "result")
  public void afterReturning(JoinPoint joinPoint, Object result) {
    String method = joinPoint.getSignature().toShortString();
    logger.info("===>> in @AfterReturning calling from method: " + method);
    logger.info("===>> result: " + result);
  }

}
