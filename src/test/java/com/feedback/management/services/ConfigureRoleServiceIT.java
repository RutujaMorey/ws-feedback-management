package com.feedback.management.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.feedback.management.collections.EventRoleRelationCollection;
import com.feedback.management.collections.UserCredentialsCollections;
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.ConfigureRoleDTO;
import com.feedback.management.dto.SendTextResponseDTO;
import com.feedback.management.repositories.EventRoleRelationRepository;
import com.feedback.management.repositories.UserAuthenticationRepository;
import com.feedback.management.repositories.UserDetailsRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ConfigureRoleServiceIT {

	@InjectMocks
	private ConfigureRoleService configureRoleService;

	@Mock
	private UserDetailsRepository userDetailsRepository;

	@Mock
	private MongoTemplate mongoTemplate;

	@Mock
	private UserAuthenticationRepository userAuthenticationRepository;

	@Mock
	private EventRoleRelationRepository eventPOCRelationRepository;

	private List<UserCredentialsCollections> userCredentialsCollection;

	private List<UserDetailsCollections> userDetailsCollections;

	@Before
	public void setUp() throws FileNotFoundException {
		MockitoAnnotations.initMocks(this);
		Gson gson = new Gson();

		userDetailsCollections = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/UserDetails.json")),
				new TypeToken<ArrayList<UserDetailsCollections>>() {
				}.getType());
		userCredentialsCollection = gson.fromJson(
				new BufferedReader(new FileReader("src/test/java/resources/UserCredentials.json")),
				new TypeToken<ArrayList<UserCredentialsCollections>>() {
				}.getType());

	}

	@Test
	public void testConfigureAddRole() throws Exception {
		ConfigureRoleDTO configureRoleDTO = new ConfigureRoleDTO("POC", "Peter.Watson@cognizant.com", "EVNT00047114");
		UserCredentialsCollections u = userCredentialsCollection.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmail())).findAny().get();
		UserDetailsCollections users = userDetailsCollections.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmailAddress())).findAny().get();

		when(userDetailsRepository.findByEmailAddress("Peter.Watson@cognizant.com")).thenReturn(users);
		when(userAuthenticationRepository.findByEmail("Peter.Watson@cognizant.com")).thenReturn(u);

		SendTextResponseDTO actual = configureRoleService.configureAddRole(configureRoleDTO);
		assertNotNull(actual);

		verify(userAuthenticationRepository, times(1)).findByEmail("Peter.Watson@cognizant.com");
		verify(userDetailsRepository, times(1)).findByEmailAddress("Peter.Watson@cognizant.com");
	}

	@Test
	public void testConfigureAddRoleFailure() throws Exception {
		ConfigureRoleDTO configureRoleDTO = new ConfigureRoleDTO("POC", "Peter.Watson@cognizant.com", "EVNT00047114");
		UserCredentialsCollections u = userCredentialsCollection.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmail())).findAny().get();

		when(userDetailsRepository.findByEmailAddress("Peter.Watson@cognizant.com")).thenReturn(null);
		when(userAuthenticationRepository.findByEmail("Peter.Watson@cognizant.com")).thenReturn(null);

		SendTextResponseDTO actual = configureRoleService.configureAddRole(configureRoleDTO);
		assertThat("", is(actual.getResponse()));
		verify(userAuthenticationRepository, times(1)).findByEmail("Peter.Watson@cognizant.com");
		verify(userDetailsRepository, times(1)).findByEmailAddress("Peter.Watson@cognizant.com");
	}

	@Test
	public void testConfigureDeleteRole() throws Exception {
		ConfigureRoleDTO configureRoleDTO = new ConfigureRoleDTO("POC", "Peter.Watson@cognizant.com", "EVNT00047114");
		UserCredentialsCollections u = userCredentialsCollection.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmail())).findAny().get();
		UserDetailsCollections users = userDetailsCollections.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmailAddress())).findAny().get();

		when(userDetailsRepository.findByEmailAddress("Peter.Watson@cognizant.com")).thenReturn(users);
		when(userAuthenticationRepository.findByEmail("Peter.Watson@cognizant.com")).thenReturn(u);

		SendTextResponseDTO actual = configureRoleService.configureDeleteRole(configureRoleDTO);
		assertNotNull(actual);

		verify(userAuthenticationRepository, times(1)).findByEmail("Peter.Watson@cognizant.com");
		verify(userDetailsRepository, times(1)).findByEmailAddress("Peter.Watson@cognizant.com");
	}

	@Test
	public void testGetEventForRoleAndEmail() throws Exception {
		EventRoleRelationCollection eventRoleRelationCollection = new EventRoleRelationCollection("EVNT00047114", "15",
				"Particpant");
		UserDetailsCollections users = userDetailsCollections.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmailAddress())).findAny().get();
		users.setEmployeeID("15");
		when(userDetailsRepository.findByEmailAddress("Peter.Watson@cognizant.com")).thenReturn(users);
		when(eventPOCRelationRepository.findByEmployeeIdAndRole("15", "Participant"))
				.thenReturn(eventRoleRelationCollection);

		SendTextResponseDTO actual = configureRoleService.getEventForRoleAndEmail("Peter.Watson@cognizant.com",
				"Participant");
		assertNotNull(actual);

		verify(userDetailsRepository, times(1)).findByEmailAddress("Peter.Watson@cognizant.com");
		verify(eventPOCRelationRepository, times(1)).findByEmployeeIdAndRole("15", "Participant");
	}

	@Test
	public void testGetEmployeeByRoleAndEvent() throws Exception {
		EventRoleRelationCollection eventRoleRelationCollection = new EventRoleRelationCollection("EVNT00047114", "15",
				"Particpant");
		List<EventRoleRelationCollection> eventRoleRelationCollections = new ArrayList<>();
		eventRoleRelationCollections.add(eventRoleRelationCollection);

		when(eventPOCRelationRepository.findByEventIdAndRole("EVNT00046588", "Participant"))
				.thenReturn(eventRoleRelationCollections);

		List<UserDetailsCollections> actual = configureRoleService.getEmployeeByRoleAndEvent("Participant",
				"EVNT00046588");
		assertNotNull(actual);

		verify(eventPOCRelationRepository, times(1)).findByEventIdAndRole("EVNT00046588", "Participant");
	}

}
