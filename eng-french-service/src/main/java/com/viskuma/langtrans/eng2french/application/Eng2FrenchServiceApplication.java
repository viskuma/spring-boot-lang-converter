package com.viskuma.langtrans.eng2french.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.viskuma.langtrans.eng2french.cache.CacheUtils;
import com.viskuma.langtrans.eng2french.db.LanguageMapEntity;

@SpringBootApplication
//@EnableDiscoveryClient
@EntityScan("com.viskuma.langtrans.eng2french.db")
public class Eng2FrenchServiceApplication implements CommandLineRunner{

	@Autowired
	private LanguageMapEntityJpaRepository languageMapEntityJpaRepository;
	
    public static void main(String[] args) {
    	SpringApplication.run(Eng2FrenchServiceApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Fetch all data from LanguageMap table
        Iterable<LanguageMapEntity> allLanguageMaps = languageMapEntityJpaRepository.findBySourceLanguageAndTargetLanguage("en_XX", "fr_XX");

        // Process retrieved data
        allLanguageMaps.forEach((languageMap) -> {
        	CacheUtils.INSTANCE.put(getKey(languageMap), languageMap);	
        });
    }
    
    public String getKey(LanguageMapEntity languageMapEntity) {
    	return languageMapEntity.toString();
    }
}


