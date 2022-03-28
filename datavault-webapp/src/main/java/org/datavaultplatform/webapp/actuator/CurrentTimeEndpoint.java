package org.datavaultplatform.webapp.actuator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id="custom-time")
public class CurrentTimeEndpoint {

  @ReadOperation
  public CurrentTime health() {
    Map<String, Object> details = new LinkedHashMap<>();
    details.put("current-time", new Date().toString());
    CurrentTime health = new CurrentTime(details);
    return health;
  }

  @ReadOperation
  public String customEndPointByName(@Selector String name) {
    return "current-time-point";
  }

  @WriteOperation
  public void writeOperation(@Selector String name) {
    //perform write operation
  }
  @DeleteOperation
  public void deleteOperation(@Selector String name){
    //delete operation
  }
}