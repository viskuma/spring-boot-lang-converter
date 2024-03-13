package com.viskuma.openai.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatRequest {

    private String model;
    private List<Message> messages;
    private Integer n;
    private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 *  Optional. Defaults to dall-e-2. The model to use for image generation.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/**
	 * @return the n
	 */
	public Integer getN() {
		return n;
	}

	/**
	 * @param n the n to set
	 * integer or null. Optional. Defaults to 1. 
	 * The number of images to generate. Must be between 1 and 10. For dall-e-3, only n=1 is supported.
	 */
	public void setN(Integer n) {
		this.n = n;
	}

	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 * temperature
	   number or null
	   Optional
	   Defaults to 1
	   What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
	   We generally recommend altering this or top_p but not both.
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public int hashCode() {
		return Objects.hash(messages, model, n, temperature);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatRequest other = (ChatRequest) obj;
		return Objects.equals(messages, other.messages) && Objects.equals(model, other.model) && n == other.n
				&& Double.doubleToLongBits(temperature) == Double.doubleToLongBits(other.temperature);
	}

	@Override
	public String toString() {
		return "ChatRequest [" + (model != null ? "model=" + model + ", " : "")
				+ (messages != null ? "messages=" + messages.toString() + ", " : "") + "n=" + n + ", temperature=" + temperature
				+ "]";
	}



    // getters and setters
}