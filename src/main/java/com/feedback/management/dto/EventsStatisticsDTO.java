package com.feedback.management.dto;

import lombok.Data;

@Data
public class EventsStatisticsDTO {

	
	private Integer totalEvents;
	private Integer livesImpacted;
	private Integer totalVolunteers;
	private Integer totalParticipants;

	public EventsStatisticsDTO(Integer totalEvents, Integer livesImpacted, Integer totalVolunteers,
			Integer totalParticipants) {
		
		
		this.totalEvents = totalEvents;
		this.livesImpacted = livesImpacted;
		this.totalVolunteers = totalVolunteers;
		this.totalParticipants = totalParticipants;
	}

	public Integer getTotalEvents() {
		return totalEvents;
	}

	public void setTotalEvents(Integer totalEvents) {
		this.totalEvents = totalEvents;
	}

	public Integer getLivesImpacted() {
		return livesImpacted;
	}

	public void setLivesImpacted(Integer livesImpacted) {
		this.livesImpacted = livesImpacted;
	}

	public Integer getTotalVolunteers() {
		return totalVolunteers;
	}

	public void setTotalVolunteers(Integer totalVolunteers) {
		this.totalVolunteers = totalVolunteers;
	}

	public Integer getTotalParticipants() {
		return totalParticipants;
	}

	public void setTotalParticipants(Integer totalParticipants) {
		this.totalParticipants = totalParticipants;
	}

}
