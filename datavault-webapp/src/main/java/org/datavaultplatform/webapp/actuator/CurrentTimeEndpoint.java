package org.datavaultplatform.webapp.actuator;

import java.time.Clock;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id="customtime")
public class CurrentTimeEndpoint {

  private final Clock clock;

  @Autowired
  public CurrentTimeEndpoint(Clock clock) {
    this.clock = clock;
  }


  @ReadOperation
  public CurrentTime health() {
    Map<String, Object> details = new LinkedHashMap<>();
    long ts = clock.millis();
    details.put("current-time", new Date(ts).toString());
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