package com.feedback.management.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "EventRoleRelation")
public class EventRoleRelationCollection {

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("eventId")
	public String eventId;

	@JsonProperty("employeeId")
	public String employeeId;

	@JsonProperty("role")
	public String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public EventRoleRelationCollection(String eventId, String employeeId, String role) {
		super();
		this.eventId = eventId;
		this.employeeId = employeeId;
		this.role = role;
	}

}
