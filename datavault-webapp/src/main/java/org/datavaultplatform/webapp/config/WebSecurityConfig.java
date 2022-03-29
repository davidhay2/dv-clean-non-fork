package org.datavaultplatform.webapp.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/resources/**").permitAll()
        .antMatchers("/error**").permitAll()
        .antMatchers("/test**").permitAll()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers("/index*").permitAll()
        .antMatchers("/auth").permitAll()
        .antMatchers("/secure*").fullyAuthenticated()
        .and()
        .formLogin();
  }
}