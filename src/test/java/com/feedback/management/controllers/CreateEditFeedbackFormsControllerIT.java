package com.feedback.management.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
import com.feedback.management.collections.FeedbackQuestionCollection;
import com.feedback.management.dto.CreateEditFeedbackDTO;
import com.feedback.management.services.CreateEditFeedbackFormService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FeedbackManagementApplication.class)
@WebMvcTest(CreateEditFeedbackFormsController.class)
public class CreateEditFeedbackFormsControllerIT {
	@Autowired
	private MockMvc mockMvc;

	private CreateEditFeedbackDTO createEditFeedbackDTO;

	private List<FeedbackQuestionCollection> feedbackQuestionCollection;

	@MockBean
	private CreateEditFeedbackFormService createEditFeedbackFormService;

	@Before
	public void setUp() throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader("src/test/java/resources/CreateEditFeedbackDTO.json"));
		createEditFeedbackDTO = gson.fromJson(br, CreateEditFeedbackDTO.class);

		feedbackQuestionCollection = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/FeedbackquestionCollection.json")),
				new TypeToken<ArrayList<FeedbackQuestionCollection>>() {
				}.getType());

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreateFeedbackForm() throws Exception {

		when(createEditFeedbackFormService.createFeedbackForm(createEditFeedbackDTO))
				.thenReturn(feedbackQuestionCollection.stream().findAny().get());
		MvcResult mvcResult = mockMvc
				.perform(post("/forms/create").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isCreated()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(createEditFeedbackFormService, times(1)).createFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testCreateFeedbackFormFailure() throws Exception {
		when(createEditFeedbackFormService.createFeedbackForm(createEditFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/forms/create").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(createEditFeedbackFormService, times(1)).createFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testEditFeedbackForm() throws Exception {

		when(createEditFeedbackFormService.editFeedbackForm(createEditFeedbackDTO))
				.thenReturn(feedbackQuestionCollection.stream().findAny().get());
		MvcResult mvcResult = mockMvc
				.perform(patch("/forms/edit").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(createEditFeedbackFormService, times(1)).editFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testEditFeedbackFormFailure() throws Exception {
		when(createEditFeedbackFormService.editFeedbackForm(createEditFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(patch("/forms/edit").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));
		verify(createEditFeedbackFormService, times(1)).editFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testDeleteFeedbackForm() throws Exception {

		when(createEditFeedbackFormService.deleteFeedbackForm(createEditFeedbackDTO))
				.thenReturn(feedbackQuestionCollection.stream().findAny().get());
		MvcResult mvcResult = mockMvc
				.perform(post("/forms/delete").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(createEditFeedbackFormService, times(1)).deleteFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testDeleteFeedbackFormFailure() throws Exception {
		when(createEditFeedbackFormService.deleteFeedbackForm(createEditFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/forms/delete").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(createEditFeedbackFormService, times(1)).deleteFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testGetFeedbackForm() throws Exception {
		when(createEditFeedbackFormService.getFeedbackForm(createEditFeedbackDTO))
				.thenReturn(feedbackQuestionCollection.stream().findAny().get());
		MvcResult mvcResult = mockMvc
				.perform(post("/forms/getform").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(createEditFeedbackFormService, times(1)).getFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testGetFeedbackFormFailure() throws Exception {
		when(createEditFeedbackFormService.getFeedbackForm(createEditFeedbackDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/forms/getform").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(createEditFeedbackDTO)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));

		verify(createEditFeedbackFormService, times(1)).getFeedbackForm(createEditFeedbackDTO);
	}

	@Test
	public void testGetAllFeedbackForm() throws Exception {
		when(createEditFeedbackFormService.getAllFeedbackForm()).thenReturn(feedbackQuestionCollection);
		MvcResult mvcResult = mockMvc.perform(get("/forms/getallform")).andExpect(status().isOk()).andDo(print())
				.andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(createEditFeedbackFormService, times(1)).getAllFeedbackForm();
	}

	@Test
	public void testGetAllFeedbackFormFailure() throws Exception {
		when(createEditFeedbackFormService.getAllFeedbackForm()).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(get("/forms/getallform").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(feedbackQuestionCollection)))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));

		verify(createEditFeedbackFormService, times(1)).getAllFeedbackForm();
	}

}
