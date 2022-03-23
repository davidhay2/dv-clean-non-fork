package org.datavaultplatform.webapp.controlllers.api;

import java.time.LocalDateTime;
import org.datavaultplatform.common.david.Time;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

  @ResponseBody
  @RequestMapping("/time")
  public Time getTime(){
      return new Time(LocalDateTime.now());
  }
}
