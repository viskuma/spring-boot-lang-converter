package com.viskuma.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	 public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eng2hindi", r -> r.path("/tohindi/**")
                        .uri("lb://eng2hindi-service/"))
                .route("eng2french", r -> r.path("/tofrench/**")
                        .uri("lb://eng2french-service"))
                .route("eng2hebrew", r -> r.path("/tohebrew/**")
                        .uri("lb://eng2hebrew-service"))
                .build();
    }
}
