package com.feedback.management.dto;

import lombok.Data;

@Data
public class ConfigureRoleDTO {

	private String role;

	private String emailAddress;
	
	private String eventId;
	

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public ConfigureRoleDTO(String role, String emailAddress, String eventId) {
		super();
		this.role = role;
		this.emailAddress = emailAddress;
		this.eventId = eventId;
	}

}
