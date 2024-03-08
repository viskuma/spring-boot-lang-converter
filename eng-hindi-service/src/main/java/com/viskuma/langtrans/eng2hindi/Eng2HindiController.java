package com.viskuma.langtrans.eng2hindi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.langtrans.eng2hindi.request.TranslationRequest;

@RestController
class Eng2HindiController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(path = "/tohindi")
    public ResponseEntity<String> convertToHindi(@RequestBody TranslationRequest requestBody) {
        // Call the language conversion service (mocked here)
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:5000/translatetext", requestBody, String.class);
        // Process the response or return it directly
        return ResponseEntity.ok(response.getBody());
    }
    
    @GetMapping(path = "/eng2hindi/message")
    public String getMessage( String requestBody) {
    	return "Hi";
    }
}
    
