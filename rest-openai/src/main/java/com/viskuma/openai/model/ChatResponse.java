package com.viskuma.openai.model;

import java.util.List;

public class ChatResponse {

    private List<Choice> choices;

    // constructors, getters and setters
    
    /**
	 * @return the choices
	 */
	public List<Choice> getChoices() {
		return choices;
	}

	/**
	 * @param choices the choices to set
	 */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public static class Choice {

        private int index;
        private Message message;
		/**
		 * @return the index
		 */
		public int getIndex() {
			return index;
		}
		/**
		 * @param index the index to set
		 */
		public void setIndex(int index) {
			this.index = index;
		}
		/**
		 * @return the message
		 */
		public Message getMessage() {
			return message;
		}
		/**
		 * @param message the message to set
		 */
		public void setMessage(Message message) {
			this.message = message;
		}

        // constructors, getters and setters
    }
}	