package org.datavaultplatform.webapp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.*;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
    //mvc.perform(get("/error")).andExpect(status().isOk());
  }

  @Test
  void testSecure() throws Exception {
    MvcResult result = mvc.perform(get("/secure")).andReturn();
    assertEquals(HttpStatus.FOUND.value(), result.getResponse().getStatus());
    //TODO - this works but seems wrong
    assertEquals("http://localhost/login", result.getResponse().getRedirectedUrl());
  }

  @Nested
  class LoginTests {

    @Test
    void success() throws Exception {
      MvcResult result = mvc.perform(formLogin().user(username).password(password))
          .andExpect(authenticated())
          .andExpect(redirectedUrl("/"))
          .andDo(print())
          .andReturn();
      SecurityContext ctx = (SecurityContext) result.getRequest().getSession().getAttribute(SPRING_SECURITY_CONTEXT_KEY);
      assertEquals(username, ctx.getAuthentication().getName());
    }

    @Test
    void fail() throws Exception {
      mvc.perform(formLogin().user(username).password("XXXX"))
          .andExpect(unauthenticated())
          .andExpect(redirectedUrl("/login?error"))
          .andDo(print());
    }

  }

}
