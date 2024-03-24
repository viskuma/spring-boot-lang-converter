package com.viskuma.openai.model;

import java.util.Objects;

public class TranslateRequest {
	
	public static int TEXT = 1;
	public static int DATE = 2;
	public static int NUMBER = 3;
	public static int CURRENCY = 4;
	public static int ADDRESS = 5;
	public static int PERSON_NAME = 6;
	public static int NATURAL_THINGS = 7;
	
	String sourceText;
	String sourceLanguage;
	String targetLanguage;
	int dataType = TEXT;

	public String getSourceText() {
		return sourceText;
	}

	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
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

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sourceLanguage, sourceText, targetLanguage);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslateRequest other = (TranslateRequest) obj;
		return Objects.equals(sourceLanguage, other.sourceLanguage) && Objects.equals(sourceText, other.sourceText)
				&& Objects.equals(targetLanguage, other.targetLanguage);
	}

	@Override
	public String toString() {
		return "TranslateRequest [" + (sourceText != null ? "sourceText=" + sourceText + ", " : "")
				+ (sourceLanguage != null ? "sourceLanguage=" + sourceLanguage + ", " : "")
				+ (targetLanguage != null ? "targetLanguage=" + targetLanguage : "") + "]";
	}
	
	public String getPrompt() {
		String prompt = "Please respond only the translated text. Translate {%s} from {%s} to {%s}";
		if(dataType == TEXT) {
			prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
			return prompt;
			
		}else if(dataType == DATE) {
			prompt = "Translate the date {%s} from {%s} into {%s} and provide only the translated output in the target language script format.";
			prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
			return prompt;
		}else if(dataType == NUMBER) {
			prompt = "Translate the numeral {%s} from {%s} into {%s}. Kindly provide the translated output using the target language script only, "
					+ "maintaining the same format as the original number and ensuring not to add any extra characters";
			prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
			return prompt;
		}else if(dataType == CURRENCY) {			
			
		}else if(dataType == ADDRESS) {
			
			prompt = "Translate the address {%s} from {%s} to {%s},"
					+ "ensuring that the meaning remains unchanged but is rewritten in the target language script only.";
			prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
			return prompt;
			
		}else if(dataType == PERSON_NAME) {
			
			prompt = "Translate the name {%s} from {%s} to {%s},"
					+ "ensuring that the meaning remains unchanged but is rewritten in the target language script only.";
			prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
			return prompt;
			
		}else if(dataType == NATURAL_THINGS) {
			prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
			return prompt;
			
		}
		prompt = String.format(prompt, getSourceText(), getSourceLanguage() ,getTargetLanguage());
		return prompt ;
			
	}

}
