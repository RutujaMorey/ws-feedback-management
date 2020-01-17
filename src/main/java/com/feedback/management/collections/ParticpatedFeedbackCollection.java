package com.feedback.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "ParticipatedFeedback")
public class ParticpatedFeedbackCollection {
	
	@Override
	public String toString() {
		return "ParticpatedFeedbackCollection [eventId=" + eventId + ", email=" + email + ", rating=" + rating
				+ ", improvements=" + improvements + ", likes=" + likes + "]";
	}

	@JsonProperty("eventId")
	private String eventId;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("rating")
	private Integer rating;
	
	public ParticpatedFeedbackCollection(String eventId, String email, Integer rating, String improvements,
			String likes) {
		super();
		this.eventId = eventId;
		this.email = email;
		this.rating = rating;
		this.improvements = improvements;
		this.likes = likes;
	}

	@JsonProperty("improvements")
	private String improvements;
	
	@JsonProperty("likes")
	private String likes;

}
