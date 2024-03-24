package com.viskuma.langtrans.eng2hindi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.langtrans.eng2hindi.cache.CacheUtils;
import com.viskuma.langtrans.eng2hindi.db.LanguageMapEntity;

@RestController
class Eng2HindiController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LanguageMapEntityJpaRepository repository;
    
    @Value("${mbart.enabled:false}")
    private String mbartEnabled;  
	
	@Value("${mbart.url:http://localhost:5000/translatetext}")
    private String mbartUrl;
    
    @Value("${openai.url:http://localhost:5001/translatetext}")
    private String openAiUrl;
    
    @CrossOrigin
    @PostMapping(path = "/tohindi")
   // @Autowired
    public ResponseEntity<String> convertToHindi(@RequestBody LanguageMapEntity requestBody) {
    	
    	if(CacheUtils.INSTANCE.get(getKey(requestBody)) != null) {
    		System.out.println("tohindi : Cache hit");
    		return ResponseEntity.ok(((LanguageMapEntity)CacheUtils.INSTANCE.get(getKey(requestBody))).getTargetText());
    	}
    	
    	String targetText = repository.findTargetTextBySourceLanguageAndTargetLanguageAndSourceText(
    			requestBody.getSourceLanguage(), requestBody.getTargetLanguage(), requestBody.getSourceText() );
    	if(targetText != null) {
    		 requestBody.setTargetText(targetText);
    		 CacheUtils.INSTANCE.put(getKey(requestBody), requestBody);
    		 System.out.println("tohindi : DB hit");
    		 return ResponseEntity.ok(targetText);
    	}
    	ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
    	if(requestBody.getSourceText().split(" ").length <= 2 && Boolean.valueOf(mbartEnabled)) {
        // Call the language conversion service (mocked here)
    		//MBart 
    		response = restTemplate.postForEntity(mbartUrl, requestBody, String.class);
    		requestBody.setModel("mbart-large-50-many-to-many-mmt");
    	}else {
    		//OpenAI
    		response = restTemplate.postForEntity(openAiUrl, requestBody, String.class);
    		//requestBody.setModel("openai-gpt-3.5-turbo");
    		System.out.println("tohindi : OpenAI hit");
    		requestBody.setModel("gpt-35");
    	}
    	 if(response.getBody() != null) {
         	requestBody.setTargetText(response.getBody());
         	repository.save(requestBody);
         }
        // Process the response or return it directly
        return ResponseEntity.ok(response.getBody());
    }
    
    @GetMapping(path = "/eng2hindi/message")
    public String getMessage( String requestBody) {
    	return "Hi";
    }
    
    public String getKey(LanguageMapEntity languageMapEntity) {
    	return languageMapEntity.toString();
    }
}
    
