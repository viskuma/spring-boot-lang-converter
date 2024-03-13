package com.viskuma.langtrans.eng2hebrew;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viskuma.langtrans.eng2hebrew.db.LanguageMapEntity;

@Repository
public interface LanguageMapEntityJpaRepository extends JpaRepository<LanguageMapEntity, Long>{

	@Query("SELECT e.targetText FROM LanguageMapEntity e WHERE e.sourceLanguage = :sourceLanguage AND e.targetLanguage = :targetLanguage AND e.sourceText = :sourceText")
    String findTargetTextBySourceLanguageAndTargetLanguageAndSourceText(@Param("sourceLanguage") String sourceLanguage, @Param("targetLanguage") String targetLanguage, @Param("sourceText") String sourceText);
	
	@Override
	public LanguageMapEntity save(LanguageMapEntity languageMapEntity);
	
	List<LanguageMapEntity> findBySourceLanguageAndTargetLanguage(String sourceLanguage, String targetLanguage);
	
}
