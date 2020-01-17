package com.feedback.management.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EventSummaryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1774463984135020277L;
	
	private String getEventID() {
		return eventID;
	}
	private void setEventID(String eventID) {
		this.eventID = eventID;
	}
	private String getMonth() {
		return month;
	}
	private void setMonth(String month) {
		this.month = month;
	}
	private String getBaseLocation() {
		return baseLocation;
	}
	private void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}
	private String getBeneficiaryName() {
		return beneficiaryName;
	}
	private void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	private String getVenueAddress() {
		return venueAddress;
	}
	private void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}
	private String getCouncilName() {
		return councilName;
	}
	private void setCouncilName(String councilName) {
		this.councilName = councilName;
	}
	private String getProject() {
		return project;
	}
	private void setProject(String project) {
		this.project = project;
	}
	private String getCategory() {
		return category;
	}
	private void setCategory(String category) {
		this.category = category;
	}
	private String getEventName() {
		return eventName;
	}
	private void setEventName(String eventName) {
		this.eventName = eventName;
	}
	private String getEventDescription() {
		return eventDescription;
	}
	private void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	private String getEventDate() {
		return eventDate;
	}
	private void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	private String getTotalNoVolunteers() {
		return totalNoVolunteers;
	}
	private void setTotalNoVolunteers(String totalNoVolunteers) {
		this.totalNoVolunteers = totalNoVolunteers;
	}
	private String getTotalVolunteersHours() {
		return totalVolunteersHours;
	}
	private void setTotalVolunteersHours(String totalVolunteersHours) {
		this.totalVolunteersHours = totalVolunteersHours;
	}
	private String getTotalTravelHours() {
		return totalTravelHours;
	}
	private void setTotalTravelHours(String totalTravelHours) {
		this.totalTravelHours = totalTravelHours;
	}
	private String getOverallVolunteeringHours() {
		return overallVolunteeringHours;
	}
	private void setOverallVolunteeringHours(String overallVolunteeringHours) {
		this.overallVolunteeringHours = overallVolunteeringHours;
	}
	private String getLivesImpacted() {
		return livesImpacted;
	}
	private void setLivesImpacted(String livesImpacted) {
		this.livesImpacted = livesImpacted;
	}
	private String getActivityType() {
		return activityType;
	}
	private void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	private String getStatus() {
		return status;
	}
	private void setStatus(String status) {
		this.status = status;
	}
	private String getpOCID() {
		return pOCID;
	}
	private void setpOCID(String pOCID) {
		this.pOCID = pOCID;
	}
	private String getpOCName() {
		return pOCName;
	}
	private void setpOCName(String pOCName) {
		this.pOCName = pOCName;
	}
	private String getpOCContactNumber() {
		return pOCContactNumber;
	}
	private void setpOCContactNumber(String pOCContactNumber) {
		this.pOCContactNumber = pOCContactNumber;
	}
	   @Id
	   private String eventID;
	   private String month;
	   private String baseLocation;
	   private String beneficiaryName;
	   private String venueAddress;
	   private String councilName;
	   private String project;
	   private String category;
	   private String eventName;
	   private String eventDescription;
	   private String eventDate;
	   private String totalNoVolunteers;
	   private String totalVolunteersHours;
	   private String totalTravelHours;
	   private String overallVolunteeringHours;
	   private String livesImpacted;
	   private String activityType;
	   private String status;
	   private String pOCID;
	   private String pOCName;
	   private String pOCContactNumber;

}
