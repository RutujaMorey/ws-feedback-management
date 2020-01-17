package com.feedback.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "UnregisteredFeedback")
public class UnregisteredFeedbackCollection {

	@Override
	public String toString() {
		return "UnregisteredFeedbackCollection [eventId=" + eventId + ", email=" + email + ", unregisteredReason="
				+ unregisteredReason + "]";
	}

	@JsonProperty("eventId")
	private String eventId;
	
	public UnregisteredFeedbackCollection(String eventId, String email, String unregisteredReason) {
		super();
		this.eventId = eventId;
		this.email = email;
		this.unregisteredReason = unregisteredReason;
	}

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("unregisteredReason")
	private String unregisteredReason;
}
