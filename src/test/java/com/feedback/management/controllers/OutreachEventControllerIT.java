package com.feedback.management.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.feedback.management.FeedbackManagementApplication;
import com.feedback.management.ObjectMapperConversion;
import com.feedback.management.collections.EventFeedBackDetailsCollections;
import com.feedback.management.collections.EventReportCollections;
import com.feedback.management.dto.EventsStatisticsDTO;
import com.feedback.management.services.OutreachEventService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FeedbackManagementApplication.class)
@WebMvcTest(OutreachEventsController.class)
public class OutreachEventControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private List<EventReportCollections> eventReportCollections;

	private List<EventFeedBackDetailsCollections> eventFeedBackDetailsCollections;

	@MockBean
	private OutreachEventService outreachEventService;

	@Before
	public void setUp() throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader("src/test/java/resources/EventReportsCollections.json"));
		eventReportCollections = gson.fromJson(br, new TypeToken<ArrayList<EventReportCollections>>() {
		}.getType());

		eventFeedBackDetailsCollections = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/FeedBackDetails.json")),
				new TypeToken<ArrayList<EventFeedBackDetailsCollections>>() {
				}.getType());
	}

	@After
	public void tearDown() {
		eventReportCollections = null;
	}

	@Test
	public void getAllOutreachEventDetails() throws Exception {
		when(outreachEventService.getAllOutreachEventDetails()).thenReturn(eventReportCollections);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/events").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventReportCollections)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(outreachEventService, times(1)).getAllOutreachEventDetails();
	}

	@Test
	public void getAllOutreachEventDetailsFailure() throws Exception {
		when(outreachEventService.getAllOutreachEventDetails()).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/events").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventReportCollections)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));

		verify(outreachEventService, times(1)).getAllOutreachEventDetails();
	}

	@Test
	public void getAllOutreachEventDetailsByEventID() throws Exception {
		EventReportCollections eventReportCollection = eventReportCollections.stream().findFirst().get();
		when(outreachEventService.getAllOutreachEventDetailsByEventID("EVNT00047261"))
				.thenReturn(eventReportCollection);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/getevent/EVNT00047261").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventReportCollections)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(outreachEventService, times(1)).getAllOutreachEventDetailsByEventID("EVNT00047261");
	}

	@Test
	public void getAllOutreachEventDetailsByEventIDFailure() throws Exception {
		when(outreachEventService.getAllOutreachEventDetailsByEventID("EVNT000484433")).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/getevent/EVNT000484433").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventReportCollections)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));

		verify(outreachEventService, times(1)).getAllOutreachEventDetailsByEventID("EVNT000484433");
	}

	@Test
	public void getEventFeedBackDetailsCollection() throws Exception {
		List<EventFeedBackDetailsCollections> filteredEventFeedBackDetailsCollections = eventFeedBackDetailsCollections
				.stream().filter(eventFeedback -> eventFeedback.getEventId().equals("EVNT00047261"))
				.collect(Collectors.toList());

		when(outreachEventService.getEventFeedBackDetailsCollection("EVNT00047261"))
				.thenReturn(filteredEventFeedBackDetailsCollections);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/getfeedbackdetails/EVNT00047261").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventFeedBackDetailsCollections)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(outreachEventService, times(1)).getEventFeedBackDetailsCollection("EVNT00047261");
	}

	@Test
	public void getEventFeedBackDetailsCollectionFailure() throws Exception {
		when(outreachEventService.getEventFeedBackDetailsCollection("EVNT000484433")).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/getfeedbackdetails/EVNT000484433").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventFeedBackDetailsCollections)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));

		verify(outreachEventService, times(1)).getEventFeedBackDetailsCollection("EVNT000484433");
	}

	@Test
	public void getEventStatistics() throws Exception {
		EventsStatisticsDTO eventsStatisticsDTO = new EventsStatisticsDTO(450, 45, 23, 5);
		when(outreachEventService.getEventStatistics()).thenReturn(eventsStatisticsDTO);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/feedback/statistics").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventsStatisticsDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(200));

		verify(outreachEventService, times(1)).getEventStatistics();
	}

	@Test
	public void getEventStatisticsFailure() throws Exception {
		EventsStatisticsDTO eventsStatisticsDTO = null;
		when(outreachEventService.getEventStatistics()).thenReturn(eventsStatisticsDTO);
		MvcResult mvcResult = mockMvc
				.perform(get("/outreach/feedback/statistics").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(eventsStatisticsDTO)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));

		verify(outreachEventService, times(1)).getEventStatistics();
	}

}
