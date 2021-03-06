package org.datavaultplatform.webapp.controllers.test.api;

import org.datavaultplatform.webapp.model.test.Person;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class FileUploadController {

  @RequestMapping(value = "/upload/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method =  RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String uploadFile(
      @RequestPart("file") MultipartFile file) {
    long size  = file.getSize();
    String type = file.getContentType();
    String name = file.getName();
    String result = String.format("name[%s]type[%s]size[%d]",name,type,size);
    System.out.println(result);
    return result;
  }

  @RequestMapping(value = "/upload/multi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method =  RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  public String uploadMulti(
      @RequestPart("file") MultipartFile file,
      @RequestPart("person") Person person) {
    long size  = file.getSize();
    String type = file.getContentType();
    String name = file.getName();
    String result = String.format("name[%s]type[%s]size[%d]first[%s]last[%s]",name,type,size,person.getFirst(),person.getLast());
    System.out.println(result);
    return result;
  }

}
