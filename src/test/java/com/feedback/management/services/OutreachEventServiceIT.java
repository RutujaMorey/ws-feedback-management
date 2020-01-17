package com.feedback.management.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.feedback.management.collections.EventFeedBackDetailsCollections;
import com.feedback.management.collections.EventReportCollections;
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.EventsStatisticsDTO;
import com.feedback.management.repositories.EventFeedbackDetailsRepository;
import com.feedback.management.repositories.EventSummaryRepository;
import com.feedback.management.repositories.UserDetailsRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OutreachEventServiceIT {

	@Mock
	private EventSummaryRepository eventSummaryRepository;

	@Mock
	private EventFeedbackDetailsRepository eventFeedbackDetailsRepository;

	@Mock
	private UserDetailsRepository userDetailsRepository;

	private List<EventReportCollections> eventReportCollections;

	private List<UserDetailsCollections> userDetailsCollections;

	private List<EventFeedBackDetailsCollections> eventFeedBackDetailsCollection;

	@InjectMocks
	private OutreachEventService outreachEventService;

	@Before
	public void setUp() throws FileNotFoundException {
		MockitoAnnotations.initMocks(this);
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader("src/test/java/resources/EventReportsCollections.json"));
		eventReportCollections = gson.fromJson(br, new TypeToken<ArrayList<EventReportCollections>>() {
		}.getType());

		eventFeedBackDetailsCollection = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/FeedBackDetails.json")),
				new TypeToken<ArrayList<EventFeedBackDetailsCollections>>() {
				}.getType());

		userDetailsCollections = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/UserDetails.json")),
				new TypeToken<ArrayList<UserDetailsCollections>>() {
				}.getType());

	}

	@After
	public void tearDown() {

	}

	@Test
	public void testGetAllOutreachEventDetails() throws Exception {
		when(eventSummaryRepository.findAll()).thenReturn(eventReportCollections);
		List<EventReportCollections> actualEventReportCollections = outreachEventService.getAllOutreachEventDetails();
		Assert.assertEquals(actualEventReportCollections, eventReportCollections);

		verify(eventSummaryRepository, times(1)).findAll();
	}

	@Test
	public void testGetEventStatistics() throws Exception {
		when(eventSummaryRepository.findAll()).thenReturn(eventReportCollections);
		List<UserDetailsCollections> participants = userDetailsCollections.stream()
				.filter(user -> "Participant".equalsIgnoreCase(user.getRole())).collect(Collectors.toList());
		when(userDetailsRepository.findByRole("Participant")).thenReturn(participants);

		EventsStatisticsDTO eventsStatisticsDTO = outreachEventService.getEventStatistics();
		assertNotNull(eventsStatisticsDTO);

		verify(eventSummaryRepository, times(1)).findAll();
		verify(userDetailsRepository, times(1)).findByRole("Participant");
	}

	@Test
	public void testGetAllOutreachEventDetailsByEventID() throws Exception {
		when(eventSummaryRepository.findByEventId("EVNT00047261"))
				.thenReturn(eventReportCollections.stream().findFirst().get());
		EventReportCollections actualEventReportCollection = outreachEventService
				.getAllOutreachEventDetailsByEventID("EVNT00047261");
		Assert.assertEquals(actualEventReportCollection, eventReportCollections.stream().findFirst().get());

		verify(eventSummaryRepository, times(1)).findByEventId("EVNT00047261");
	}

	@Test
	public void testGetEventFeedBackDetailsCollection() throws Exception {
		when(eventFeedbackDetailsRepository.findByEventId("EVNT00047261")).thenReturn(eventFeedBackDetailsCollection);
		List<EventFeedBackDetailsCollections> actualEventFeedBackDetailsCollections = outreachEventService
				.getEventFeedBackDetailsCollection("EVNT00047261");
		Assert.assertEquals(actualEventFeedBackDetailsCollections, eventFeedBackDetailsCollection);

		verify(eventFeedbackDetailsRepository, times(1)).findByEventId("EVNT00047261");
	}

}
