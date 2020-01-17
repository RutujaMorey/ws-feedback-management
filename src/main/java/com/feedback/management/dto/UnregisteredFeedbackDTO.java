package com.feedback.management.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UnregisteredFeedbackDTO implements Serializable {

	public UnregisteredFeedbackDTO(String eventId, String email, String unregisteredReason) {
		super();
		this.eventId = eventId;
		this.email = email;
		this.unregisteredReason = unregisteredReason;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnregisteredReason() {
		return unregisteredReason;
	}

	public void setUnregisteredReason(String unregisteredReason) {
		this.unregisteredReason = unregisteredReason;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1438262088903709116L;

	private String eventId;

	private String email;

	private String unregisteredReason;

}
