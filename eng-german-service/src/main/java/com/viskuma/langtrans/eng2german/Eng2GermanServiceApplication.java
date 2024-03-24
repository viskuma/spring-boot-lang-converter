package com.viskuma.langtrans.eng2german;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.viskuma.langtrans.eng2german.cache.CacheUtils;
import com.viskuma.langtrans.eng2german.db.LanguageMapEntity;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.viskuma"})
@EntityScan("com.viskuma.langtrans.eng2german.db")
public class Eng2GermanServiceApplication implements CommandLineRunner{

	@Autowired
	private LanguageMapEntityJpaRepository languageMapEntityJpaRepository;
	
	
    public static void main(String[] args) {
    	// System.setProperty("spring.profiles.active", "eng2german");
        SpringApplication.run(Eng2GermanServiceApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Fetch all data from LanguageMap table
        Iterable<LanguageMapEntity> allLanguageMaps = languageMapEntityJpaRepository.findBySourceLanguageAndTargetLanguage("en_XX", "de_DE");

        // Process retrieved data
        allLanguageMaps.forEach((languageMap) -> {
        	CacheUtils.INSTANCE.put(getKey(languageMap), languageMap);	
        });
    }
    
    public String getKey(LanguageMapEntity languageMapEntity) {
    	return languageMapEntity.toString();
    }
}
