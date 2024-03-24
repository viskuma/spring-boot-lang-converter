package com.viskuma.langtrans.eng2hindi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        List allowedOrigins = new ArrayList();
        allowedOrigins.add("*");
        configuration.setAllowedOriginPatterns(allowedOrigins); // Replace with your allowed origins
        configuration.setAllowedMethods(allowedOrigins); // Allow all HTTP methods
        configuration.setAllowedHeaders(allowedOrigins); // Allow all headers
        configuration.setAllowCredentials(true); // Allow credentials (e.g., cookies)
       // allowedOriginPatterns
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}