package com.fs7.finalproject.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EshopApplication {
  public static void main(String[] args) {
    SpringApplication.run(EshopApplication.class, args);
  }
}
