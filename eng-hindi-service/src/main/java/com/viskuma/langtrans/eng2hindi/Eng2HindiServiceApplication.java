package com.viskuma.langtrans.eng2hindi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hazelcast.cache.CacheUtil;
import com.viskuma.langtrans.eng2hindi.cache.CacheUtils;
import com.viskuma.langtrans.eng2hindi.db.LanguageMapEntity;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.viskuma"})
public class Eng2HindiServiceApplication implements CommandLineRunner{

	@Autowired
	private LanguageMapEntityJpaRepository languageMapEntityJpaRepository;
	
    public static void main(String[] args) {
    	// System.setProperty("spring.profiles.active", "eng2hindi");
        SpringApplication.run(Eng2HindiServiceApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Fetch all data from LanguageMap table
        Iterable<LanguageMapEntity> allLanguageMaps = languageMapEntityJpaRepository.findBySourceLanguageAndTargetLanguage("en_XX", "hi_IN");

        // Process retrieved data
        allLanguageMaps.forEach((languageMap) -> {
        	CacheUtils.INSTANCE.put(getKey(languageMap), languageMap);	
        });
    }
    
    public String getKey(LanguageMapEntity languageMapEntity) {
    	return languageMapEntity.toString();
    }
}
