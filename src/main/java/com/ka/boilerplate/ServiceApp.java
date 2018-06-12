package com.ka.boilerplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ka.boilerplate.response.Order;
import com.ka.boilerplate.service.MysqlService;

@SpringBootApplication
public class ServiceApp implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

  @Autowired
  MysqlService mysqlService;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(ServiceApp.class, args);
  }

  @Override
  public void run(String... arg0) throws Exception {
    for (int i = 0; i < 2; i++) {
      Order order = mysqlService.save();
      logger.info(order.toString());
      Thread.sleep(1000);
    }
    while (true) {
      logger.info("Beep");
      Thread.sleep(1000);
    }
  }
}

// mvn clean
// mvn -Dservice package
// java -jar target/service-boilerplate-0.0.1-SNAPSHOT.jar
