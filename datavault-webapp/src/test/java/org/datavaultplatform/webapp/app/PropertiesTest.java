package org.datavaultplatform.webapp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AddTestProperties
public class PropertiesTest {

  @Test
  void testApplicationProperties(@Value("${spring.application.name}") String appName) {
    assertEquals("DV-WEB-APP", appName);
  }

  @Test
  void testDataVaultProperties(@Value("${webapp.logout.url}") String logoutUrl) {
    assertEquals("/auth/confirmation", logoutUrl);
  }

}
