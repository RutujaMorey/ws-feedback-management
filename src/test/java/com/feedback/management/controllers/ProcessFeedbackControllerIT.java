package com.feedback.management.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileNotFoundException;

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
import com.feedback.management.collections.NotParticipatedFeedbackCollection;
import com.feedback.management.collections.ParticpatedFeedbackCollection;
import com.feedback.management.collections.UnregisteredFeedbackCollection;
import com.feedback.management.dto.NotParticipatedFeedbackDTO;
import com.feedback.management.dto.ParticipatedFeedbackDTO;
import com.feedback.management.dto.UnregisteredFeedbackDTO;
import com.feedback.management.services.ProcessFeedbackService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FeedbackManagementApplication.class)
@WebMvcTest(ProcessFeedbackController.class)
public class ProcessFeedbackControllerIT {

	@Autowired
	private MockMvc mockMvc;

	private ParticpatedFeedbackCollection particpatedFeedbackCollection;

	private NotParticipatedFeedbackCollection notParticipatedFeedbackCollection;

	private UnregisteredFeedbackCollection unregisteredFeedbackCollection;

	@MockBean
	private ProcessFeedbackService processFeedbackService;

	@Before
	public void setUp() throws FileNotFoundException {
		particpatedFeedbackCollection = new ParticpatedFeedbackCollection("EVNT00047114", "Peter.Watson@cognizant.com",
				4, "Sporting activity", "time could be changed.");
		notParticipatedFeedbackCollection = new NotParticipatedFeedbackCollection("EVNT00047114",
				"Peter.Watson@cognizant.com", "Cant Disclose");
		unregisteredFeedbackCollection = new UnregisteredFeedbackCollection("EVNT00047114",
				"Peter.Watson@cognizant.com", "Cant Disclose");
	}

	@After
	public void tearDown() {

	}

	@Test
	public void testProcessParticpatedFeedback() throws Exception {

		ParticipatedFeedbackDTO participatedFeedbackDTO = new ParticipatedFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", 4, "Sporting activity", "time could be changed.");
		when(processFeedbackService.processParticpatedFeedback(participatedFeedbackDTO))
				.thenReturn(particpatedFeedbackCollection);
		MvcResult mvcResult = mockMvc
				.perform(post("/feedback/participated").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(participatedFeedbackDTO)))
				.andExpect(status().isCreated()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(processFeedbackService, times(1)).processParticpatedFeedback(participatedFeedbackDTO);
	}

	@Test
	public void testProcessParticpatedFeedbackFailure() throws Exception {
		ParticipatedFeedbackDTO participatedFeedbackDTO = new ParticipatedFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", 4, "Sporting activity", "time could be changed.");
		when(processFeedbackService.processParticpatedFeedback(participatedFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/feedback/participated").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(participatedFeedbackDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(processFeedbackService, times(1)).processParticpatedFeedback(participatedFeedbackDTO);
	}

	@Test
	public void testProcessNotParticpatedFeedback() throws Exception {

		NotParticipatedFeedbackDTO notParticipatedFeedbackDTO = new NotParticipatedFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", "Cant Disclose");
		when(processFeedbackService.processNotParticpatedFeedback(notParticipatedFeedbackDTO))
				.thenReturn(notParticipatedFeedbackCollection);
		MvcResult mvcResult = mockMvc
				.perform(post("/feedback/notparticipated").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(notParticipatedFeedbackDTO)))
				.andExpect(status().isCreated()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(processFeedbackService, times(1)).processNotParticpatedFeedback(notParticipatedFeedbackDTO);
	}

	@Test
	public void testProcessNotParticpatedFeedbackFailure() throws Exception {
		NotParticipatedFeedbackDTO notParticipatedFeedbackDTO = new NotParticipatedFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", "Cant Disclose");
		when(processFeedbackService.processNotParticpatedFeedback(notParticipatedFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/feedback/notparticipated").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(notParticipatedFeedbackDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(processFeedbackService, times(1)).processNotParticpatedFeedback(notParticipatedFeedbackDTO);
	}

	@Test
	public void testProcessUnregisteredFeedback() throws Exception {

		UnregisteredFeedbackDTO unregisteredFeedbackDTO = new UnregisteredFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", "Cant Disclose");
		when(processFeedbackService.processUnregisteredFeedback(unregisteredFeedbackDTO))
				.thenReturn(unregisteredFeedbackCollection);
		MvcResult mvcResult = mockMvc
				.perform(post("/feedback/unregistered").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(unregisteredFeedbackDTO)))
				.andExpect(status().isCreated()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(processFeedbackService, times(1)).processUnregisteredFeedback(unregisteredFeedbackDTO);
	}

	@Test
	public void testProcessUnregisteredFeedbackFailure() throws Exception {
		UnregisteredFeedbackDTO unregisteredFeedbackDTO = new UnregisteredFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", "Cant Disclose");
		when(processFeedbackService.processUnregisteredFeedback(unregisteredFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/feedback/unregistered").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(unregisteredFeedbackDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(processFeedbackService, times(1)).processUnregisteredFeedback(unregisteredFeedbackDTO);
	}

}
