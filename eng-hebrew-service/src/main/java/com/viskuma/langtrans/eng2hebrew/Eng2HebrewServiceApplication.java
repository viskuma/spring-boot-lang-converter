package com.viskuma.langtrans.eng2hebrew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.viskuma"})
public class Eng2HebrewServiceApplication {

    public static void main(String[] args) {
    	// System.setProperty("spring.profiles.active", "eng2hebrew");
        SpringApplication.run(Eng2HebrewServiceApplication.class, args);
    }
}
