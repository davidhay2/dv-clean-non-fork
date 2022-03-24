package org.datavaultplatform.webapp.controllers;

public class Person {

  private final String first;
  private final String last;

  public Person(String first, String last){
    this.first = first;
    this.last = last;
  }

  public String getFirst(){
    return first;
  }

  public String getLast(){
    return last;
  }

}
