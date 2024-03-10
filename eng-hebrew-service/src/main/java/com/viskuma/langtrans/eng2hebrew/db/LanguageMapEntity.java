package com.viskuma.langtrans.eng2hebrew.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
//@IdClass(LanguageMapEntity.class)
@Table(name = "LANGUAGE_MAP_ENTITY")

public class LanguageMapEntity {

	private String sourceLanguage = "en_XX";  
	private String targetLanguage = "hi_IN";  
	private String sourceText ;
	private String targetText ;
	private String model;
	
	//https://thorben-janssen.com/jpa-generate-primary-keys/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	
	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}
	public String getTargetLanguage() {
		return targetLanguage;
	}
	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}
	public String getSourceText() {
		return sourceText;
	}
	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}
	public String getTargetText() {
		return targetText;
	}
	public void setTargetText(String targetText) {
		this.targetText = targetText;
	}
	

    public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
		
}
