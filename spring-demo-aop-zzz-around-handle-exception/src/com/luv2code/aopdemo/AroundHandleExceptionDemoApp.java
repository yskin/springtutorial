package com.luv2code.aopdemo;

import java.util.logging.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.luv2code.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

  private static Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

  public static void main(String[] args) {
    // read spring config java class
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(DemoConfig.class);
    // get the bean from spring container
    TrafficFortuneService fortuneService =
        context.getBean("trafficFortuneService", TrafficFortuneService.class);
    logger.info("Main Program: AroundDemoApp");
    logger.info("Call fortuneService.getFortune");
    boolean tripWire = true;
    String fortune = fortuneService.getFortune(tripWire);
    logger.info("MyFortune: " + fortune);
    logger.info("Finished");
    // close the context
    context.close();
  }

}
