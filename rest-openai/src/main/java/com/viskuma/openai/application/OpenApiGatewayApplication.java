package com.viskuma.openai.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenApiGatewayApplication {

	 public static void main(String[] args) {
		 System.out.println(System.getenv());
		 
        SpringApplication.run(OpenApiGatewayApplication.class, args);
    }

 
}
