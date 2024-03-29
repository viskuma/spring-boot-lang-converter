package com.viskuma.eureka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaLanguageConverter {

	 public static void main(String[] args) {
        SpringApplication.run(EurekaLanguageConverter.class, args);
    }

   
}
