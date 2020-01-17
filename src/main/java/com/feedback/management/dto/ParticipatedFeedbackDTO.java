package com.feedback.management.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ParticipatedFeedbackDTO implements Serializable {

	public ParticipatedFeedbackDTO(String eventId, String email, Integer rating, String improvements, String likes) {
		super();
		this.eventId = eventId;
		this.email = email;
		this.rating = rating;
		this.improvements = improvements;
		this.likes = likes;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1282274672333397263L;

	private String eventId;

	private String email;

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



	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getImprovements() {
		return improvements;
	}

	public void setImprovements(String improvements) {
		this.improvements = improvements;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	private Integer rating;

	private String improvements;

	private String likes;
}
