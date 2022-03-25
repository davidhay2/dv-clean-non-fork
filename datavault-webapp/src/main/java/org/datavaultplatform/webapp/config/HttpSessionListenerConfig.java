package org.datavaultplatform.webapp.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpSessionListenerConfig implements HttpSessionListener {

  private static final Logger LOG = LoggerFactory.getLogger(HttpSessionListenerConfig.class);

  @Override
  public void sessionCreated(HttpSessionEvent event) {
      LOG.info("sessionCreated {}",event.getSession().getId());
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
    LOG.info("sessionDestroyed {}",event.getSession().getId());
  }

}