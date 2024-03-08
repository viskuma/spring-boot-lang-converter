package com.viskuma.langtrans.eng2french.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.langtrans.eng2french.request.TranslationRequest;

@RestController
class Eng2FrenchController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/tofrench")
    public ResponseEntity<String> convertToFrench(@RequestBody TranslationRequest requestBody) {
        // Call the language conversion service (mocked here)
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:5000/translatetext", requestBody, String.class);
        // Process the response or return it directly
        return ResponseEntity.ok(response.getBody());
    }
}