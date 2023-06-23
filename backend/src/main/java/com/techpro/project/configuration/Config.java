package com.techpro.project.configuration;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Config {

  @PostConstruct
  public void doInit() {
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~WORKING~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
  }
}
