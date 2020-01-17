package com.feedback.management.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;

import com.feedback.management.collections.UserCredentialsCollections;
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.UserCredentialsDTO;
import com.feedback.management.dto.UserDetailsDTO;
import com.feedback.management.repositories.UserAuthenticationRepository;
import com.feedback.management.repositories.UserDetailsRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserAuthenticationServiceIT {
	@Mock
	private UserAuthenticationRepository userAuthenticationRepository;

	@Mock
	private MongoOperations mongoOperation;

	@Mock
	private UserDetailsRepository userDetailsRepository;

	private List<UserCredentialsCollections> userCredentialsCollection;

	private List<UserDetailsCollections> userDetailsCollections;
	private UserCredentialsDTO userCredentialsDTO;
	private UserDetailsDTO userDetailsDTO;

	@InjectMocks
	private UserAuthenticationService userAuthenticationService;

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
		userCredentialsDTO = new UserCredentialsDTO("Peter.Watson@cognizant.com", "peter$$");
		userDetailsDTO = new UserDetailsDTO("15", "Peter", "Watson", "Peter.Watson@cognizant.com", "93748237343",
				"Participant", "peter$$");

	}

	@After
	public void tearDown() {

	}

	@Test
	public void testAuthenticateUser() throws Exception {
		UserCredentialsCollections u = userCredentialsCollection.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmail())).findAny().get();
		UserDetailsCollections users = userDetailsCollections.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmailAddress())).findAny().get();

		when(userAuthenticationRepository.findByEmail("Peter.Watson@cognizant.com")).thenReturn(u);
		when(userDetailsRepository.findByEmailAddress("Peter.Watson@cognizant.com")).thenReturn(users);

		UserDetailsCollections actual = userAuthenticationService.authenticateUser(userCredentialsDTO);
		assertNotNull(actual);

		verify(userAuthenticationRepository, times(1)).findByEmail("Peter.Watson@cognizant.com");
		verify(userDetailsRepository, times(1)).findByEmailAddress("Peter.Watson@cognizant.com");
	}

	@Test
	public void testAuthenticateUserFailure() throws Exception {

		when(userAuthenticationRepository.findByEmail("Peter.Watson@cognizant.com")).thenReturn(null);
		UserDetailsCollections actual = userAuthenticationService.authenticateUser(userCredentialsDTO);
		assertNull(actual);

		verify(userAuthenticationRepository, times(1)).findByEmail("Peter.Watson@cognizant.com");
		verify(userDetailsRepository, times(0)).findByEmailAddress("Peter.Watson@cognizant.com");
	}

	@Test
	public void testAuthenticateUserAuthenticationFailure() throws Exception {
		userCredentialsDTO.setPassword("***");
		UserCredentialsCollections u = userCredentialsCollection.stream()
				.filter(user -> "Peter.Watson@cognizant.com".equals(user.getEmail())).findAny().get();
		when(userAuthenticationRepository.findByEmail("Peter.Watson@cognizant.com")).thenReturn(u);

		UserDetailsCollections actual = userAuthenticationService.authenticateUser(userCredentialsDTO);
		assertNull(actual);

		verify(userAuthenticationRepository, times(1)).findByEmail("Peter.Watson@cognizant.com");
		verify(userDetailsRepository, times(0)).findByEmailAddress("Peter.Watson@cognizant.com");
	}

	@Test
	public void testCreateUser() throws Exception {
		UserDetailsDTO newuserDetailsDTO = new UserDetailsDTO("19", "Abc", "xyz", "Abc.xyz@cognizant.com",
				"93748237343", "Participant", "$$");
		
		UserCredentialsCollections u = new UserCredentialsCollections("Abc.xyz@cognizant.com", "$$", "Participant");
		UserDetailsCollections users = new UserDetailsCollections("Abc", "xyz", "Abc.xyz@cognizant.com", "93748237343",
				"Participant", "19");
		users.setEmployeeID("19");
		when(userAuthenticationRepository.save(any(UserCredentialsCollections.class))).thenReturn(u);
		when(userDetailsRepository.save(any(UserDetailsCollections.class))).thenReturn(users);

		UserDetailsCollections actual = userAuthenticationService.createUser(newuserDetailsDTO);
		assertEquals(users, actual);
		
	}

	public long generateSequence() throws Exception {

		long employeeId = userAuthenticationService.generateSequence("hosting");
		return employeeId;
	}

}
