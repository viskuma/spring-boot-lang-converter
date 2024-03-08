package com.viskuma.langtrans.eng2french.request;

public class TranslationRequest {

	private String text;
    private String source_lang = "en_XX";
    private String target_lang = "fr_XX";
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource_lang() {
		return source_lang;
	}
	public void setSource_lang(String source_lang) {
		this.source_lang = source_lang;
	}
	public String getTarget_lang() {
		return target_lang;
	}
	public void setTarget_lang(String target_lang) {
		this.target_lang = target_lang;
	}

	
}
