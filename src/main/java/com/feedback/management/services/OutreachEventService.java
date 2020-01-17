package com.feedback.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.feedback.management.collections.EventFeedBackDetailsCollections;
import com.feedback.management.collections.EventReportCollections;
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.EventsStatisticsDTO;
import com.feedback.management.repositories.EventFeedbackDetailsRepository;
import com.feedback.management.repositories.EventSummaryRepository;
import com.feedback.management.repositories.UserDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OutreachEventService {

	@Autowired
	private EventSummaryRepository eventSummaryRepository;

	@Autowired
	private EventFeedbackDetailsRepository eventFeedbackDetailsRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private MongoOperations mongoOps;

	public List<EventReportCollections> getAllOutreachEventDetails() {
		return eventSummaryRepository.findAll();

	}

	public EventsStatisticsDTO getEventStatistics() {
		List<EventReportCollections> eventReportCollections = eventSummaryRepository.findAll();
		if (!CollectionUtils.isEmpty(eventReportCollections)) {
			EventsStatisticsDTO eventsStatisticsDTO = new EventsStatisticsDTO(0, 0, 0, 0);
			eventsStatisticsDTO.setTotalEvents(eventReportCollections.size());
			eventsStatisticsDTO.setTotalVolunteers(eventReportCollections.stream()
					.map(EventReportCollections::getTotalNoVolunteers).mapToInt(Integer::parseInt).sum());
			eventsStatisticsDTO.setLivesImpacted(eventReportCollections.stream()
					.map(EventReportCollections::getLivesImpacted).mapToInt(Integer::parseInt).sum());

			List<UserDetailsCollections> userDetailsCollections = userDetailsRepository.findByRole("Participant");
			eventsStatisticsDTO.setTotalParticipants(userDetailsCollections.size());
			return eventsStatisticsDTO;
		}
		return null;
	}

	public EventReportCollections getAllOutreachEventDetailsByEventID(String eventId) {

		return eventSummaryRepository.findByEventId(eventId);

	}

	public List<EventFeedBackDetailsCollections> getEventFeedBackDetailsCollection(String eventId) {
		return eventFeedbackDetailsRepository.findByEventId(eventId);

	}

	

}
