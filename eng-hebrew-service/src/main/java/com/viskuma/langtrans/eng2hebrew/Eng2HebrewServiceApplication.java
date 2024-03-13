package com.viskuma.langtrans.eng2hebrew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.viskuma.langtrans.eng2hebrew.cache.CacheUtils;
import com.viskuma.langtrans.eng2hebrew.db.LanguageMapEntity;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.viskuma"})
@EntityScan("com.viskuma.langtrans.eng2hebrew.db")
public class Eng2HebrewServiceApplication implements CommandLineRunner{

	@Autowired
	private LanguageMapEntityJpaRepository languageMapEntityJpaRepository;
	
	
    public static void main(String[] args) {
    	// System.setProperty("spring.profiles.active", "eng2hebrew");
        SpringApplication.run(Eng2HebrewServiceApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Fetch all data from LanguageMap table
        Iterable<LanguageMapEntity> allLanguageMaps = languageMapEntityJpaRepository.findBySourceLanguageAndTargetLanguage("en_XX", "he_IL");

        // Process retrieved data
        allLanguageMaps.forEach((languageMap) -> {
        	CacheUtils.INSTANCE.put(getKey(languageMap), languageMap);	
        });
    }
    
    public String getKey(LanguageMapEntity languageMapEntity) {
    	return languageMapEntity.toString();
    }
}
