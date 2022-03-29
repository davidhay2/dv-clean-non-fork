package org.datavaultplatform.webapp.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.test.context.TestPropertySource;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@TestPropertySource(
    locations = "classpath:application-test.properties",
    properties = "spring.main.allow-bean-definition-overriding=true")
public @interface AddTestProperties {

}
