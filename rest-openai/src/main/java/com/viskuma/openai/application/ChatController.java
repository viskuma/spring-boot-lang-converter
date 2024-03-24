package com.viskuma.openai.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.openai.model.ChatRequest;
import com.viskuma.openai.model.ChatResponse;
import com.viskuma.openai.model.TranslateRequest;

@RestController
public class ChatController {
    
    @Qualifier("openaiRestTemplatePersonal")
    @Autowired
    private RestTemplate restTemplatePersonal;
    
    @Qualifier("openaiRestTemplateProxyPersonal")
    @Autowired
    private RestTemplate restTemplateProxyPersonal;
    
    @Qualifier("openaiRestTemplateExternal")
    @Autowired
    private RestTemplate restTemplateExternal;
    
    @Qualifier("openaiRestTemplateProxyExternal")
    @Autowired
    private RestTemplate restTemplateProxyExternal;
    
    @Value("${openai.model}")
    private String model;
    
    @Value("${openai.api.url.personal}")
    private String apiUrlPersonal;
    
    @Value("${openai.api.url.external}")
    private String apiUrlExternal;
    
    @Value("${proxyenabled:false}")
    private String proxyEnabled;
    
    // personal - 0 and external = 1
    @Value("${openai.api.type:0}")
    private String openApiType;
    
    
    @PostMapping("/translatetext")
  	public String translateText(@RequestBody TranslateRequest requestBody) {
//    	if(trustStoreFilePath != null || !trustStoreFilePath.isEmpty()) {
//	    	System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
//	    	System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
//    	}
    	String prompt = requestBody.getPrompt();
    	return chat(prompt);
    }
    
    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);
        ChatResponse response = null;
        String apiUrl = apiUrlPersonal;
        RestTemplate restTemplate = restTemplatePersonal;
        if( openApiType.equals("0") ) {
        	apiUrl = apiUrlPersonal;
        	if(Boolean.valueOf(proxyEnabled)) {
        		restTemplate = restTemplateProxyPersonal;
        	}
        }else {
        	apiUrl = apiUrlExternal;
        	restTemplate = restTemplateExternal;
        	/*if(Boolean.valueOf(proxyEnabled)) {
        		restTemplate = restTemplateProxyExternal;
        	}*/
        }
        
        
        System.out.println("ChatRequest -> "+ request);
        // call the API
//        if(Boolean.valueOf(proxyEnabled)) {
//        	 response = restTemplateProxy.postForObject(apiUrl, request, ChatResponse.class);
//        }
//        else {
//        	response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
//        }
        
        response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }
        
        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}