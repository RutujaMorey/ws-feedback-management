package com.feedback.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "EventReports")
public class EventReportCollections {

	@JsonProperty("eventId")
	private String eventId;

	@JsonProperty("month")
	private String month;

	@JsonProperty("baseLocation")
	private String baseLocation;

	@JsonProperty("beneficiaryName")
	private String beneficiaryName;

	@JsonProperty("venueAddress")
	private String venueAddress;

	@JsonProperty("councilName")
	private String councilName;

	@JsonProperty("project")
	private String project;

	@JsonProperty("category")
	private String category;

	@JsonProperty("eventName")
	private String eventName;

	@JsonProperty("eventDescription")
	private String eventDescription;

	@JsonProperty("eventDate")
	private String eventDate;

	@JsonProperty("totalNoVolunteers")
	private String totalNoVolunteers;

	@JsonProperty("totalVolunteersHours")
	private String totalVolunteersHours;

	@JsonProperty("totalTravelHours")
	private String totalTravelHours;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getCouncilName() {
		return councilName;
	}

	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getTotalNoVolunteers() {
		return totalNoVolunteers;
	}

	public void setTotalNoVolunteers(String totalNoVolunteers) {
		this.totalNoVolunteers = totalNoVolunteers;
	}

	public String getTotalVolunteersHours() {
		return totalVolunteersHours;
	}

	public void setTotalVolunteersHours(String totalVolunteersHours) {
		this.totalVolunteersHours = totalVolunteersHours;
	}

	public String getTotalTravelHours() {
		return totalTravelHours;
	}

	public void setTotalTravelHours(String totalTravelHours) {
		this.totalTravelHours = totalTravelHours;
	}

	public String getOverallVolunteeringHours() {
		return overallVolunteeringHours;
	}

	public void setOverallVolunteeringHours(String overallVolunteeringHours) {
		this.overallVolunteeringHours = overallVolunteeringHours;
	}

	public String getLivesImpacted() {
		return livesImpacted;
	}

	public void setLivesImpacted(String livesImpacted) {
		this.livesImpacted = livesImpacted;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getPocContactNumber() {
		return pocContactNumber;
	}

	public void setPocContactNumber(String pocContactNumber) {
		this.pocContactNumber = pocContactNumber;
	}

	@JsonProperty("overallVolunteeringHours")
	private String overallVolunteeringHours;

	@JsonProperty("livesImpacted")
	private String livesImpacted;

	@JsonProperty("activityType")
	private String activityType;

	@JsonProperty("status")
	private String status;

	@JsonProperty("pocId")
	private String pocId;

	@JsonProperty("pocName")
	private String pocName;

	@JsonProperty("pocContactNumber")
	private String pocContactNumber;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getStatus() {
		return status;
	}

	public String getPocId() {
		return pocId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPocId(String pocId) {
		this.pocId = pocId;
	}

}
