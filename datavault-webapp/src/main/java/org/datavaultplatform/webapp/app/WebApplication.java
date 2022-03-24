package org.datavaultplatform.webapp.app;

import org.datavaultplatform.webapp.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan({"org.datavaultplatform.webapp.controllers"})
@Import({WebConfig.class})
public class WebApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebApplication.class, args);
  }

}