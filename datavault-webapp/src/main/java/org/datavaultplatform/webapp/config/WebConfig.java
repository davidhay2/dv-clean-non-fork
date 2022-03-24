package org.datavaultplatform.webapp.config;

import java.nio.charset.StandardCharsets;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class WebConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
      CommonsRequestLoggingFilter filter
          = new CommonsRequestLoggingFilter();
      filter.setIncludeQueryString(true);
      filter.setIncludePayload(true);
      filter.setMaxPayloadLength(1000000);
      filter.setIncludeHeaders(true);
      filter.setBeforeMessagePrefix("BEFORE MESSAGE :");
      filter.setAfterMessagePrefix("AFTER MESSAGE : ");
      return filter;
    }

  @Bean
  public HttpSessionEventPublisher httpSessionEventPublisher() {
    return new HttpSessionEventPublisher();
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding(StandardCharsets.UTF_8.name());
    filter.setForceEncoding(true);

    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(filter);
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }


}
