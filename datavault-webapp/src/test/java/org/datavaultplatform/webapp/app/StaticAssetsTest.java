package org.datavaultplatform.webapp.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest
@AutoConfigureMockMvc
@AddTestProperties
public class StaticAssetsTest {

  private static final MediaType JAVASCRIPT = MediaType.parseMediaType("application/javascript");
  private static final MediaType CSS = MediaType.parseMediaType("text/css");

  @Autowired
  MockMvc mvc;

  @Test
  void testStaticJavascript() throws Exception {

    MvcResult res = mvc.perform(get("/js/helloWorld.js"))
        .andExpect(content().string(Matchers.containsString("Hello World!")))
        .andDo(print())
        .andReturn();

    MediaType actual = MediaType.parseMediaType(res.getResponse().getContentType());
    Assertions.assertTrue(actual.isCompatibleWith(JAVASCRIPT));
  }

  @Test
  void testStaticCSS() throws Exception {

    MvcResult res = mvc.perform(get("/css/main.css"))
        .andExpect(content().string(Matchers.containsString("hello-title")))
        .andDo(print())
        .andReturn();

    MediaType actual = MediaType.parseMediaType(res.getResponse().getContentType());
    Assertions.assertTrue(actual.isCompatibleWith(CSS));
  }
}
