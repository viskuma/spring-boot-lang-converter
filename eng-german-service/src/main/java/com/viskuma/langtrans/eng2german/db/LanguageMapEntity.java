package com.viskuma.langtrans.eng2german.db;

import java.util.Objects;

import com.viskuma.langtrans.eng2german.db.LanguageMapEntity;

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
	private String targetLanguage = "de_DE";  
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id, model, sourceLanguage, sourceText, targetLanguage, targetText);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LanguageMapEntity other = (LanguageMapEntity) obj;
		return Objects.equals(id, other.id) && Objects.equals(model, other.model)
				&& Objects.equals(sourceLanguage, other.sourceLanguage) && Objects.equals(sourceText, other.sourceText)
				&& Objects.equals(targetLanguage, other.targetLanguage) && Objects.equals(targetText, other.targetText);
	}

	@Override
	public String toString() {
		return "LanguageMapEntity [" + (sourceLanguage != null ? "sourceLanguage=" + sourceLanguage + ", " : "")
				+ (targetLanguage != null ? "targetLanguage=" + targetLanguage + ", " : "")
				+ (sourceText != null ? "sourceText=" + sourceText : "") + "]";
	}
		
}
