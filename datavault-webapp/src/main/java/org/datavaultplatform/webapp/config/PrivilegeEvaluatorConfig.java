package org.datavaultplatform.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;

//TODO - this is temporary so that the layout will work
@Configuration
public class PrivilegeEvaluatorConfig {

  @Bean
  WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator(){
    return new WebInvocationPrivilegeEvaluator() {
      @Override
      public boolean isAllowed(String uri, Authentication authentication) {
        return true;
      }

      @Override
      public boolean isAllowed(String contextPath, String uri, String method,
          Authentication authentication) {
        return true;
      }
    };
  }
}
