package com.feedback.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "NotParticipatedFeedback")
public class NotParticipatedFeedbackCollection {
	@Override
	public String toString() {
		return "NotParticipatedFeedbackCollection [eventId=" + eventId + ", email=" + email + ", notParticpatedReason="
				+ notParticpatedReason + "]";
	}

	@JsonProperty("eventId")
	private String eventId;

	public NotParticipatedFeedbackCollection(String eventId, String email, String notParticpatedReason) {
		super();
		this.eventId = eventId;
		this.email = email;
		this.notParticpatedReason = notParticpatedReason;
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

	public String getNotParticpatedReason() {
		return notParticpatedReason;
	}

	public void setNotParticpatedReason(String notParticpatedReason) {
		this.notParticpatedReason = notParticpatedReason;
	}

	@JsonProperty("email")
	private String email;

	@JsonProperty("notParticpatedReason")
	private String notParticpatedReason;

}
