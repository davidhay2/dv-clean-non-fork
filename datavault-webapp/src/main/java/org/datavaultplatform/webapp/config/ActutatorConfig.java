package org.datavaultplatform.webapp.config;

import org.datavaultplatform.webapp.actuator.CurrentTimeEndpoint;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActutatorConfig {

  @Bean
  CurrentTimeEndpoint currentTime(){
      return new CurrentTimeEndpoint();
  }

}
