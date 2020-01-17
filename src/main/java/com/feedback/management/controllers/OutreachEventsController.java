package com.feedback.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.management.collections.EventFeedBackDetailsCollections;
import com.feedback.management.collections.EventReportCollections;
import com.feedback.management.dto.EventsStatisticsDTO;
import com.feedback.management.services.OutreachEventService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/outreach")
@AllArgsConstructor
public class OutreachEventsController {
	
	@Autowired
	private OutreachEventService outreachEventService;
	

	@GetMapping("/events")
	public ResponseEntity<List<EventReportCollections>> getAllOutreachEventDetails() {
		List<EventReportCollections> eventReportCollections = outreachEventService.getAllOutreachEventDetails();
		return CollectionUtils.isEmpty(eventReportCollections)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(eventReportCollections, HttpStatus.OK);
	}

	@GetMapping("/getevent/{eventId}")
	public ResponseEntity<EventReportCollections> getOutreachEventDetailsForEvent(@PathVariable("eventId") String eventId) {
		EventReportCollections eventReportCollection = outreachEventService.getAllOutreachEventDetailsByEventID(eventId);
		return ObjectUtils.isEmpty(eventReportCollection)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(eventReportCollection, HttpStatus.OK);
	}
	@GetMapping("/getfeedbackdetails/{eventId}")
	public ResponseEntity<List<EventFeedBackDetailsCollections>> getEventFeedBackDetailsCollection(@PathVariable("eventId") String eventId) {
		List<EventFeedBackDetailsCollections> eventFeedBackDetailsCollection = outreachEventService.getEventFeedBackDetailsCollection(eventId);
		return CollectionUtils.isEmpty(eventFeedBackDetailsCollection)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(eventFeedBackDetailsCollection, HttpStatus.OK);
	}
	
	@GetMapping("/feedback/statistics")
	public ResponseEntity<EventsStatisticsDTO> getEventStatistics() {
		EventsStatisticsDTO eventsStatisticsDTO = outreachEventService.getEventStatistics();
		return ObjectUtils.isEmpty(eventsStatisticsDTO)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(eventsStatisticsDTO, HttpStatus.OK);
	}
	
	
	

}
