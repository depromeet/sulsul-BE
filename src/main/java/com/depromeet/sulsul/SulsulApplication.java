package com.depromeet.sulsul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ServletComponentScan
@EnableJpaAuditing
@SpringBootApplication
public class SulsulApplication {

  public static void main(String[] args) {
    SpringApplication.run(SulsulApplication.class, args);
  }

}
