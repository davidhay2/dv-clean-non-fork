package org.datavaultplatform.webapp.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import freemarker.template.Configuration;
import freemarker.template.utility.XmlEscape;
import java.io.IOException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@WebMvcTest
@AddTestProperties
public class FreemarkerConfigTest {

  Field fPrefix = UrlBasedViewResolver.class.getDeclaredField("prefix");
  Field fSuffix = UrlBasedViewResolver.class.getDeclaredField("suffix");
  Field fContentType = UrlBasedViewResolver.class.getDeclaredField("contentType");

  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;

  @Autowired
  private FreeMarkerViewResolver freeMarkerViewResolver;

  {
    fPrefix.setAccessible(true);
    fSuffix.setAccessible(true);
    fContentType.setAccessible(true);
  }

  public FreemarkerConfigTest() throws NoSuchFieldException {
  }

  @Test
  void testFreemarkerConfigurer() throws IOException {
    assertNotNull(freeMarkerConfigurer);
    Configuration config = freeMarkerConfigurer.getConfiguration();

    Object helloTemplateSource = config.getTemplateLoader().findTemplateSource("hello.ftl");
    String helloTemplateSourceStr = helloTemplateSource.toString();
    Assertions.assertEquals("class path resource [freemarker/hello.ftl]", helloTemplateSourceStr);

    XmlEscape esc = (XmlEscape) config.getSharedVariable("xml_escape");
    Assertions.assertNotNull(esc);

    Assertions.assertEquals("UTF-8", config.getURLEscapingCharset());
  }

  @Test
  void testViewResolver() throws IllegalAccessException {

    //VIEW RESOLVER CONFIG
    String prefix = (String) fPrefix.get(freeMarkerViewResolver);
    Assertions.assertEquals("", prefix);

    String suffix = (String) fSuffix.get(freeMarkerViewResolver);
    Assertions.assertEquals(".ftl", suffix);

    String contentType = (String) fContentType.get(freeMarkerViewResolver);
    Assertions.assertEquals("text/html;charset=UTF-8", contentType);
  }


}
