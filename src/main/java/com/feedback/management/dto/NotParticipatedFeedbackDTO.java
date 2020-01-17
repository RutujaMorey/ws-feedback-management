package com.feedback.management.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class NotParticipatedFeedbackDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2512071192667431484L;

	

	public NotParticipatedFeedbackDTO(String eventId, String email, String notParticpatedReason) {
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

	private String eventId;

	private String email;

	private String notParticpatedReason;

}
