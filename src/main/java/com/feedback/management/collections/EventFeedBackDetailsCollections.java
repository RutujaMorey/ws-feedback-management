package com.feedback.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "FeedBackDetails")
public class EventFeedBackDetailsCollections{

	@JsonProperty("eventId")
	private String eventId;
	
	@JsonProperty("feedbackType")
	private String feedbackType;
	
	@JsonProperty("likes")
	private String likes;

	@JsonProperty("dislikes")
	private String dislikes;

	@JsonProperty("rating")
	private String rating;

	@JsonProperty("notParticipatedReason")
	private String notParticipatedReason;
	
	@JsonProperty("unregisteredReason")
	private String unregisteredReason;
	
	@JsonProperty("employeeId")
	private String employeeId;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getUnregisteredReason() {
		return unregisteredReason;
	}

	public void setUnregisteredReason(String unregisteredReason) {
		this.unregisteredReason = unregisteredReason;
	}
	public String getNotParticipatedReason() {
		return notParticipatedReason;
	}

	public void setNotParticipatedReason(String notParticipatedReason) {
		this.notParticipatedReason = notParticipatedReason;
	}
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}
	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}
	

}
