package com.viskuma.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
//@CrossOrigin
public class ApiGatewayApplication {

	 public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

	// @CrossOrigin
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("eng2hindi", r -> r.path("/tohindi/**")
                        .uri("lb://eng2hindi-service/"))
                .route("eng2french", r -> r.path("/tofrench/**")
                        .uri("lb://eng2french-service"))
                .route("eng2hebrew", r -> r.path("/tohebrew/**")
                        .uri("lb://eng2hebrew-service"))
                .route("eng2german", r -> r.path("/togerman/**")
                        .uri("lb://eng2german-service"))
                
                .build();
    }
    
//    @Bean
//    public WebFluxConfigurer corsConfigurer() {
//        return new WebFluxConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                		.allowedOriginPatterns("*")
//                        .allowedOrigins("*") // Add your frontend domain here
//                        .allowedMethods("*")
//                        .allowedHeaders("*");
//                        //.allowCredentials(true);
//                
//                
//            }
//        };
//    }	
}
