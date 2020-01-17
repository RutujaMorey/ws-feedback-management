package com.feedback.management.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.feedback.management.collections.NotParticipatedFeedbackCollection;
import com.feedback.management.collections.ParticpatedFeedbackCollection;
import com.feedback.management.collections.UnregisteredFeedbackCollection;
import com.feedback.management.dto.NotParticipatedFeedbackDTO;
import com.feedback.management.dto.ParticipatedFeedbackDTO;
import com.feedback.management.dto.UnregisteredFeedbackDTO;
import com.feedback.management.repositories.NotParticipatedFeedbackRepository;
import com.feedback.management.repositories.ParticpatedFeedbackRepository;
import com.feedback.management.repositories.UnregisteredFeedbackRepository;

public class ProcessFeedbackServiceIT {

	@InjectMocks
	private ProcessFeedbackService processFeedbackService;

	@Mock
	private ParticpatedFeedbackRepository particpatedFeedbackRepository;

	@Mock
	private NotParticipatedFeedbackRepository notParticipatedFeedbackRepository;

	@Mock
	private UnregisteredFeedbackRepository unregisteredFeedbackRepository;

	private NotParticipatedFeedbackDTO notParticipatedFeedbackDTO;

	private UnregisteredFeedbackDTO unregisteredFeedbackDTO;

	private UnregisteredFeedbackCollection unregisteredFeedbackCollection;

	private NotParticipatedFeedbackCollection notParticipatedFeedbackCollection;

	@Before
	public void setUp() throws FileNotFoundException {
		MockitoAnnotations.initMocks(this);

		notParticipatedFeedbackDTO = new NotParticipatedFeedbackDTO("EVNT00047114", "Peter.Watson@cognizant.com",
				"Do not wish to disclose");
		unregisteredFeedbackDTO = new UnregisteredFeedbackDTO("EVNT00047114", "Peter.Watson@cognizant.com",
				"Do not wish to disclose");
		unregisteredFeedbackCollection = new UnregisteredFeedbackCollection("EVNT00047114",
				"Peter.Watson@cognizant.com", "Do not wish to disclose");
		notParticipatedFeedbackCollection = new NotParticipatedFeedbackCollection("EVNT00047114",
				"Peter.Watson@cognizant.com", "Do not wish to disclose");

	}

	@After
	public void tearDown() {

		notParticipatedFeedbackDTO = null;
		unregisteredFeedbackDTO = null;
	}

	@Test
	public void testProcessParticpatedFeedback() throws Exception {
		ParticipatedFeedbackDTO participatedFeedbackDTO = new ParticipatedFeedbackDTO("EVNT00047114",
				"Peter.Watson@cognizant.com", 5, "", "");

		ParticpatedFeedbackCollection particpatedFeedbackCollection = new ParticpatedFeedbackCollection("EVNT00047114",
				"Peter.Watson@cognizant.com", 5, "", "");
		when(particpatedFeedbackRepository.save(any(ParticpatedFeedbackCollection.class)))
				.thenReturn(particpatedFeedbackCollection);
		ParticpatedFeedbackCollection actual = processFeedbackService
				.processParticpatedFeedback(participatedFeedbackDTO);

		assertEquals(particpatedFeedbackCollection, actual);

	}

	@Test
	public void testProcessNotParticpatedFeedback() throws Exception {

		when(notParticipatedFeedbackRepository.save(any(NotParticipatedFeedbackCollection.class)))
				.thenReturn(notParticipatedFeedbackCollection);

		NotParticipatedFeedbackCollection actual = processFeedbackService
				.processNotParticpatedFeedback(notParticipatedFeedbackDTO);

		assertEquals(notParticipatedFeedbackCollection, actual);
	}

	@Test
	public void testProcessUnregisteredFeedback() throws Exception {

		when(unregisteredFeedbackRepository.save(any(UnregisteredFeedbackCollection.class)))
				.thenReturn(unregisteredFeedbackCollection);
		UnregisteredFeedbackCollection actual = processFeedbackService
				.processUnregisteredFeedback(unregisteredFeedbackDTO);

		assertEquals(unregisteredFeedbackCollection, actual);

	}

}
