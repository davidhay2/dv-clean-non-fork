package org.datavaultplatform.webapp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@AddTestProperties
public class LoginTest {

  @Autowired
  MockMvc mvc;

  @Value("${spring.security.user.name}")
  String username;

  @Value("${spring.security.user.password}")
  String password;

  @Test
  void testUnsecure() throws Exception {
    mvc.perform(get("/index")).andExpect(status().isOk());
    mvc.perform(get("/test/hello")).andExpect(status().isOk());
    mvc.perform(get("/error")).andExpect(status().isInternalServerError());
  }

  @Test
  void testSecure() throws Exception {
    MvcResult result = mvc.perform(get("/secure")).andReturn();
    assertEquals(HttpStatus.FOUND.value(), result.getResponse().getStatus());
    assertEquals("http://localhost/auth/login", result.getResponse().getRedirectedUrl());
  }

  @Nested
  class LoginTests {

    ResultActions login(String username, String password) throws Exception {
      return mvc.perform(formLogin().loginProcessingUrl("/auth/security_check").user(username).password(password));
    }

    @Test
    void success() throws Exception {
      MvcResult result =
          login(username, password)
          .andExpect(authenticated())
          .andExpect(redirectedUrl("/"))
          .andDo(print())
          .andReturn();
      SecurityContext ctx = (SecurityContext) result.getRequest().getSession().getAttribute(SPRING_SECURITY_CONTEXT_KEY);
      assertEquals(username, ctx.getAuthentication().getName());
    }

    @Test
    void fail() throws Exception {
      login(username, "XXXX")
          .andExpect(unauthenticated())
          .andExpect(redirectedUrl("/auth/login?error=true"))
          .andDo(print());
    }

  }

}
