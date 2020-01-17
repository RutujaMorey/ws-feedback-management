package com.feedback.management.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.feedback.management.dto.ConfigureRoleDTO;
import com.feedback.management.dto.SendTextResponseDTO;
import com.feedback.management.services.ConfigureRoleService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FeedbackManagementApplication.class)
@WebMvcTest(ConfigureRolesController.class)
public class ConfigureRolesControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConfigureRoleService configureRoleService;

	private ConfigureRoleDTO configureRoleDTO;
	private SendTextResponseDTO addResponse;
	private SendTextResponseDTO deleteResponse;
	private List<UserDetailsCollections> userDetailsCollections;

	@Before
	public void setUp() throws FileNotFoundException {
		Gson gson = new Gson();
		configureRoleDTO = new ConfigureRoleDTO("POC", "Peter.Watson@cognizant.com", "EVNT00046588");
		addResponse = new SendTextResponseDTO(
				"Peter.Watson@cognizant.com added successfully as POC for event EVNT00046588");
		deleteResponse = new SendTextResponseDTO("Peter.Watson@cognizant.com revoked as POC for event EVNT00046588");
		userDetailsCollections = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/UserDetails.json")),
				new TypeToken<ArrayList<UserDetailsCollections>>() {
				}.getType());

	}

	@After
	public void tearDown() {
	}

	@Test
	public void testConfigureAddRole() throws Exception {

		when(configureRoleService.configureAddRole(configureRoleDTO)).thenReturn(addResponse);
		MvcResult mvcResult = mockMvc
				.perform(post("/config/add").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(configureRoleDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(configureRoleService, times(1)).configureAddRole(configureRoleDTO);
	}

	@Test
	public void testConfigureAddRoleFailure() throws Exception {
		when(configureRoleService.configureAddRole(configureRoleDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/config/add").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(configureRoleDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(configureRoleService, times(1)).configureAddRole(configureRoleDTO);
	}

	@Test
	public void testConfigureDeleteRole() throws Exception {

		when(configureRoleService.configureDeleteRole(configureRoleDTO)).thenReturn(deleteResponse);
		MvcResult mvcResult = mockMvc
				.perform(post("/config/delete").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(configureRoleDTO)))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(configureRoleService, times(1)).configureDeleteRole(configureRoleDTO);
	}

	@Test
	public void testConfigureDeleteRoleFailure() throws Exception {
		when(configureRoleService.configureAddRole(configureRoleDTO)).thenReturn(null);
		MvcResult mvcResult = mockMvc
				.perform(post("/config/delete").contentType(MediaType.APPLICATION_JSON)
						.content(ObjectMapperConversion.jsonToString(configureRoleDTO)))
				.andExpect(status().isBadRequest()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(400));
		verify(configureRoleService, times(1)).configureDeleteRole(configureRoleDTO);
	}

	@Test
	public void testGetEventForRoleAndEmail() throws Exception {
		when(configureRoleService.getEventForRoleAndEmail("Peter.Watson@cognizant.com", "POC"))
				.thenReturn(new SendTextResponseDTO("EVNT00047114"));
		MvcResult mvcResult = mockMvc.perform(get("/config/fetcheventfor/Peter.Watson@cognizant.com/POC"))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(configureRoleService, times(1)).getEventForRoleAndEmail("Peter.Watson@cognizant.com", "POC");
	}

	@Test
	public void testGetEventForRoleAndEmailFailure() throws Exception {
		when(configureRoleService.getEventForRoleAndEmail("Peter.Watson@cognizant.com", "POC")).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(get("/config/fetcheventfor/Peter.Watson@cognizant.com/POC"))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));
		verify(configureRoleService, times(1)).getEventForRoleAndEmail("Peter.Watson@cognizant.com", "POC");
	}

	@Test
	public void testGetEmployeesForeventAndRole() throws Exception {
		when(configureRoleService.getEmployeeByRoleAndEvent("EVNT00047114", "POC")).thenReturn(userDetailsCollections);
		MvcResult mvcResult = mockMvc.perform(get("/config/fetchemployeesfor/EVNT00047114/POC"))
				.andExpect(status().isOk()).andDo(print()).andReturn();
		assertNotNull(mvcResult.getResponse());
		verify(configureRoleService, times(1)).getEmployeeByRoleAndEvent("EVNT00047114", "POC");
	}

	@Test
	public void testGetEmployeesForeventAndRoleFailure() throws Exception {
		when(configureRoleService.getEmployeeByRoleAndEvent("EVNT00047114", "POC")).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(get("/config/fetchemployeesfor/EVNT00047114/POC"))
				.andExpect(status().isNoContent()).andDo(print()).andReturn();

		assertThat(mvcResult.getResponse().getStatus(), is(204));
		verify(configureRoleService, times(1)).getEmployeeByRoleAndEvent("EVNT00047114", "POC");
	}
}
