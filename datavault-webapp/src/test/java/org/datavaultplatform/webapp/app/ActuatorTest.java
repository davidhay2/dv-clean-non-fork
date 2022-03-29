package org.datavaultplatform.webapp.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AddTestProperties
@AutoConfigureMockMvc
public class ActuatorTest {

  @Autowired
  MockMvc mvc;

  @Value("#{'${management.endpoints.web.exposure.include}'.split(',')}")
  private Set<String> endpoints;

  @Test
  void testInfo() throws Exception {
    mvc.perform(get("/actuator/info"))
        .andExpect(jsonPath("$.app.name").value(Matchers.is("datavault-webapp")))
        .andExpect(jsonPath("$.app.description").value(Matchers.is("webapp for datavault")))
        .andExpect(jsonPath("$.git.commit.time").exists())
        .andExpect(jsonPath("$.git.commit.id").exists())
        .andExpect(jsonPath("$.build.artifact").value(Matchers.is("datavault-webapp")))
        .andExpect(jsonPath("$.build.time").exists())
        .andExpect(jsonPath("$.java.vendor").exists())
        .andExpect(jsonPath("$.java.runtime.version").exists())
        .andExpect(jsonPath("$.java.jvm.version").exists())
        .andDo(print());
  }

  @Test
  void testCurrentTime() throws Exception {
    mvc.perform(get("/actuator/custom-time"))
        .andExpect(jsonPath("$.current-time").exists())
        .andDo(print());
  }

  @Test
  void testEndpoints() throws Exception {

    assertEquals(ImmutableSet.of("env","beans","info","health","custom-time"), endpoints);

    ResultActions temp = mvc.perform(get("/actuator"))
        .andExpect(status().isOk()).andDo(print());

    for(String endpoint : endpoints){
      temp.andExpect(jsonPath("$._links."+endpoint).exists());
    }

  }
}
