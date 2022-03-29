package org.datavaultplatform.webapp.controllers.test.api;

import java.time.LocalDateTime;
import org.datavaultplatform.common.david.Time;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class SimulateErrorController {

  @ResponseBody
  @RequestMapping("/oops")
  public String throwError(){
    if(1==1){
      throw new RuntimeException("SimulatedError");
    }
    return "oops";
  }
}
