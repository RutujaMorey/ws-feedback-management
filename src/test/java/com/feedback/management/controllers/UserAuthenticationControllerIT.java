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
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.UserCredentialsDTO;
import com.feedback.management.dto.UserDetailsDTO;
import com.feedback.management.services.UserAuthenticationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FeedbackManagementApplication.class)
@WebMvcTest(UserAuthenticationController.class)
public class UserAuthenticationControllerIT {
	@Autowired
	private MockMvc mockMvc;

	private List<UserDetailsCollections> userDetailsCollections;

	@MockBean
	private UserAuthenticationService userAuthenticationService;

	@Before
	public void setUp() throws FileNotFoundException {
		Gson gson = new Gson();

		userDetailsCollections = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/UserDetails.json")),
				new TypeToken<ArrayList<UserDetailsCollections>>() {
				}.getType());
	}

	@After
	public void tearDown() {
		userDetailsCollections = null;
	}

	@Test
	public void testAuthenticateUser() throws Exception {
		UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO("Peter.Watson@cognizant.com", "peter$$");
		when(userAuthenticationService.authenticateUser(userCredentialsDTO))
				.thenReturn(userDetailsCollections.stream().findFirst().get());
		MvcResult mvcResult = mockMvc
				.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(userCredentialsDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(userAuthenticationService, times(1)).authenticateUser(userCredentialsDTO);
	}

	@Test
	public void testAuthenticateUserFailure() throws Exception {
		UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO("Peter.Watson@cognizant.com", "peter$$");
		when(userAuthenticationService.authenticateUser(userCredentialsDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(userCredentialsDTO)))
				.andExpect(status().isUnauthorized()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(401));
		verify(userAuthenticationService, times(1)).authenticateUser(userCredentialsDTO);
	}

	@Test
	public void testCreateUser() throws Exception {

		UserDetailsDTO userDetailsDTO = new UserDetailsDTO("15", "Peter", "Watson", "Peter.Watson@cognizant.com",
				"93748237343", "Participant", "peter$$");
		when(userAuthenticationService.createUser(userDetailsDTO))
				.thenReturn(userDetailsCollections.stream().findFirst().get());
		MvcResult mvcResult = mockMvc
				.perform(post("/user/signup").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(userDetailsDTO)))
				.andExpect(status().isCreated()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(userAuthenticationService, times(1)).createUser(userDetailsDTO);
	}

	@Test
	public void testCreateUserFailure() throws Exception {
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO("15", "Peter", "Watson", "Peter.Watson@cognizant.com",
				"93748237343", "Participant", "peter$$");
		when(userAuthenticationService.createUser(userDetailsDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/user/signup").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(userDetailsDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(userAuthenticationService, times(1)).createUser(userDetailsDTO);
	}

}
