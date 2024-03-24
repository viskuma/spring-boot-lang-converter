package com.viskuma.openai.application;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIRestTemplateConfig {

    @Value("${openai.api.key.personal}")
    private String openaiApiKey;
    
    
    @Value("${truststorefilepath}")
    private String trustStoreFilePath;
    
    @Value("${truststorepassword:password}")
    private String trustStorePassword;

    @Bean
    @Qualifier("openaiRestTemplatePersonal")
    public RestTemplate openaiRestTemplatePersonal() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
    
    
    @Bean
    @Qualifier("openaiRestTemplateProxyPersonal")
    public RestTemplate openaiRestTemplateProxyPersonal() {
    	
    	System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
    	System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    	
    	Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("STLFTPPROXY", 8080));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);
        
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            return execution.execute(request, body);
        });
        return restTemplate;
    }
    
    @Bean
    @Qualifier("openaiRestTemplateExternal")
    public RestTemplate openaiRestTemplateExternal() {
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
//            return execution.execute(request, body);
//        });
        return restTemplate;
    }
    
    
    @Bean
    @Qualifier("openaiRestTemplateProxyExternal")
    public RestTemplate openaiRestTemplateProxy() {
    	
    	System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
    	System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    	
    	Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("STLFTPPROXY", 8080));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);
        
        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        restTemplate.getInterceptors().add((request, body, execution) -> {
//            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
//            return execution.execute(request, body);
//        });
        return restTemplate;
    }
    
}