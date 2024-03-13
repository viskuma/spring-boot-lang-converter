package com.viskuma.openai.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.openai.model.ChatRequest;
import com.viskuma.openai.model.ChatResponse;

@RestController
public class ChatController {
    
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;
    
    @Qualifier("openaiRestTemplateProxy")
    @Autowired
    private RestTemplate restTemplateProxy;
    
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url}")
    private String apiUrl;
    
    @Value("${proxyenabled:false}")
    private String proxyEnabled;
  
    
    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);
        ChatResponse response = null;
        
        System.out.println("ChatRequest -> "+ request);
        // call the API
        if(Boolean.valueOf(proxyEnabled)) {
        	 response = restTemplateProxy.postForObject(apiUrl, request, ChatResponse.class);
        }
        else {
        	response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        }
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        
        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}