package com.viskuma.openai.model;

import java.util.Objects;

public class Message {

   
	private String role;
    private String content;
    
    public Message(String role, String prompt) {
		// TODO Auto-generated constructor stub
    	this.role = role;
    	this.content = prompt;
	}
    
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 * role string Required. The role of the entity that is creating the message. Currently only user is supported.
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set.
	 * content string Required. The content of the message.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(content, other.content) && Objects.equals(role, other.role);
	}

	@Override
	public String toString() {
		return "Message [" + (role != null ? "role=" + role + ", " : "") + (content != null ? "content=" + content : "")
				+ "]";
	}

    // constructor, getters and setters
}