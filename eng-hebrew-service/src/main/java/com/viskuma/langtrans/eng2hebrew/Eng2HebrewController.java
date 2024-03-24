package com.viskuma.langtrans.eng2hebrew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.viskuma.langtrans.eng2hebrew.cache.CacheUtils;
import com.viskuma.langtrans.eng2hebrew.db.LanguageMapEntity;

@RestController
class Eng2HebrewController {

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
    @PostMapping(path = "/tohebrew")
   // @Autowired
    public ResponseEntity<String> convertToHebrew(@RequestBody LanguageMapEntity requestBody) {
    	
    	if(CacheUtils.INSTANCE.get(getKey(requestBody)) != null) {
    		System.out.println("tohebrew : Cache hit");
    		return ResponseEntity.ok(((LanguageMapEntity)CacheUtils.INSTANCE.get(getKey(requestBody))).getTargetText());
    	}
    	
    	String targetText = repository.findTargetTextBySourceLanguageAndTargetLanguageAndSourceText(
    			requestBody.getSourceLanguage(), requestBody.getTargetLanguage(), requestBody.getSourceText() );
    	if(targetText != null) {
    		 requestBody.setTargetText(targetText);
   		 	 CacheUtils.INSTANCE.put(getKey(requestBody), requestBody);
   		 	System.out.println("tohebrew : DB hit");
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
    		System.out.println("tohebrew : OpenAI hit");
    		requestBody.setModel("gpt-35");
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
    
