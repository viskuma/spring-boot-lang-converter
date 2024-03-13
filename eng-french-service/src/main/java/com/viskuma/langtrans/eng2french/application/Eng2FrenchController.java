package com.viskuma.langtrans.eng2french.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.langtrans.eng2french.cache.CacheUtils;
import com.viskuma.langtrans.eng2french.db.LanguageMapEntity;

@RestController
class Eng2FrenchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LanguageMapEntityJpaRepository repository;
    
    @PostMapping("/tofrench")
    public ResponseEntity<String> convertToFrench(@RequestBody LanguageMapEntity requestBody) {
    	
    	if(CacheUtils.INSTANCE.get(getKey(requestBody)) != null) {
    		return ResponseEntity.ok(((LanguageMapEntity)CacheUtils.INSTANCE.get(getKey(requestBody))).getTargetText());
    	}
    	
    	String targetText = repository.findTargetTextBySourceLanguageAndTargetLanguageAndSourceText(
    			requestBody.getSourceLanguage(), requestBody.getTargetLanguage(), requestBody.getSourceText() );
    	if(targetText != null) {
    		 requestBody.setTargetText(targetText);
   		 	 CacheUtils.INSTANCE.put(getKey(requestBody), requestBody);
    		 return ResponseEntity.ok(targetText);
    	}
    	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
    	if(requestBody.getSourceText().split(" ").length <= 2) {
        // Call the language conversion service (mocked here)
    		//MBart 
    		response = restTemplate.postForEntity("http://localhost:5000/translatetext", requestBody, String.class);
    		requestBody.setModel("mbart-large-50-many-to-many-mmt");
    	}else {
    		//OpenAI
    		response = restTemplate.postForEntity("http://localhost:5001/translatetext", requestBody, String.class);
    		requestBody.setModel("openai-gpt-3.5-turbo");
    	}
        if(response.getBody() != null) {
        	requestBody.setTargetText(response.getBody());
        	repository.save(requestBody);
        }
        // Process the response or return it directly
        return ResponseEntity.ok(response.getBody());
    }
    
    public String getKey(LanguageMapEntity languageMapEntity) {
    	return languageMapEntity.toString();
    }
}