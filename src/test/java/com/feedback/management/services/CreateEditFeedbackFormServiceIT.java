package com.feedback.management.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.feedback.management.collections.FeedbackQuestionCollection;
import com.feedback.management.dto.CreateEditFeedbackDTO;
import com.feedback.management.dto.QuestionAnswerDTO;
import com.feedback.management.repositories.CreateEditFeedbackFormRepository;
import com.google.common.base.Equivalence.Wrapper;

public class CreateEditFeedbackFormServiceIT {
	@InjectMocks
	private CreateEditFeedbackFormService createEditFeedbackFormService;

	@Mock
	private MongoTemplate mongoTemplate;

	@Mock
	private CreateEditFeedbackFormRepository createEditFeedbackFormRepository;

	@Before
	public void setUp() throws FileNotFoundException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateFeedbackForm() throws Exception {
		List<String> answers = new ArrayList<>();
		answers.add("Did not Recieve Further Information About the Event");
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(
				"Hey there, Please share Your feedback for unregistering from the event.", answers);
		CreateEditFeedbackDTO createEditFeedbackDTO = new CreateEditFeedbackDTO("EVNT00047114", "Not Participated",
				questionAnswerDTO, "Multiple Choice One answer");
		FeedbackQuestionCollection feedbackQuestionCollection = new FeedbackQuestionCollection("EVNT00047114",
				"Not Participated", "Multiple Choice One answer", questionAnswerDTO);
		when(createEditFeedbackFormRepository.save(feedbackQuestionCollection)).thenReturn(feedbackQuestionCollection);

		FeedbackQuestionCollection actual = createEditFeedbackFormService.createFeedbackForm(createEditFeedbackDTO);

//		verify(createEditFeedbackFormRepository, times(1)).save(feedbackQuestionCollection);
	}

	@Test
	public void testGetAllFeedbackForm() throws Exception {
		List<String> answers = new ArrayList<>();
		answers.add("Did not Recieve Further Information About the Event");
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(
				"Hey there, Please share Your feedback for unregistering from the event.", answers);

		FeedbackQuestionCollection feedbackQuestionCollection = new FeedbackQuestionCollection("EVNT00047114",
				"Not Participated", "Multiple Choice One answer", questionAnswerDTO);
		List<FeedbackQuestionCollection> feedbackQuestionCollections = new ArrayList<>();
		feedbackQuestionCollections.add(feedbackQuestionCollection);
		when(createEditFeedbackFormRepository.findAll()).thenReturn(feedbackQuestionCollections);
		List<FeedbackQuestionCollection> actualFeedbackQuestionCollections = createEditFeedbackFormService
				.getAllFeedbackForm();
		Assert.assertEquals(actualFeedbackQuestionCollections, feedbackQuestionCollections);

		verify(createEditFeedbackFormRepository, times(1)).findAll();
	}

	@Test
	public void testGetFeedbackForm() throws Exception {

		List<String> answers = new ArrayList<>();
		answers.add("Did not Recieve Further Information About the Event");
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(
				"Hey there, Please share Your feedback for unregistering from the event.", answers);
		CreateEditFeedbackDTO createEditFeedbackDTO = new CreateEditFeedbackDTO("EVNT00047114", "Not Participated",
				questionAnswerDTO, "Multiple Choice One answer");
		FeedbackQuestionCollection feedbackQuestionCollection = new FeedbackQuestionCollection("EVNT00047114",
				"Not Participated", "Multiple Choice One answer", questionAnswerDTO);
		Query query = new Query();
		query.addCriteria(Criteria.where("eventId").is(createEditFeedbackDTO.getEventId()).and("feedbackType")
				.is(createEditFeedbackDTO.getFeedbackType()));
		List<FeedbackQuestionCollection> feedbackQuestionCollections = new ArrayList<>();
		feedbackQuestionCollections.add(feedbackQuestionCollection);
//		when(mongoTemplate.findOne()).thenReturn(feedbackQuestionCollections);
		FeedbackQuestionCollection actualFeedbackQuestionCollection = createEditFeedbackFormService
				.getFeedbackForm(createEditFeedbackDTO);
//		Assert.assertEquals(actualFeedbackQuestionCollection, feedbackQuestionCollections);

		verify(mongoTemplate, times(1)).findOne(query, FeedbackQuestionCollection.class);
	}

	@Test
	public void testEditFeedbackForm() throws Exception {
		List<String> answers = new ArrayList<>();
		answers.add("Did not Recieve Further Information About the Event");
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(
				"Hey there, Please share Your feedback for unregistering from the event.", answers);
		CreateEditFeedbackDTO createEditFeedbackDTO = new CreateEditFeedbackDTO("EVNT00047114", "Not Participated",
				questionAnswerDTO, "Multiple Choice One answer");
		Query query = new Query();

		query.addCriteria(Criteria.where("eventId").is(createEditFeedbackDTO.getEventId()).and("feedbackType")
				.is(createEditFeedbackDTO.getFeedbackType()));
		Update update = new Update();

		update.set("questionType", createEditFeedbackDTO.getQuestionType());
		update.set("questionAnswers", createEditFeedbackDTO.getQuestionAnswer());

		FeedbackQuestionCollection feedbackQuestionCollection = new FeedbackQuestionCollection("EVNT00047114",
				"Not Participated", "Multiple Choice One answer", questionAnswerDTO);
		when(createEditFeedbackFormRepository.save(feedbackQuestionCollection)).thenReturn(feedbackQuestionCollection);

		FeedbackQuestionCollection actual = createEditFeedbackFormService.editFeedbackForm(createEditFeedbackDTO);

		verify(mongoTemplate, times(1)).updateFirst(query, update, FeedbackQuestionCollection.class);
		verify(mongoTemplate, times(1)).findOne(query, FeedbackQuestionCollection.class);
	}

	@Test
	public void testDeleteFeedbackForm() throws Exception {
		List<String> answers = new ArrayList<>();
		answers.add("Did not Recieve Further Information About the Event");
		QuestionAnswerDTO questionAnswerDTO = new QuestionAnswerDTO(
				"Hey there, Please share Your feedback for unregistering from the event.", answers);
		CreateEditFeedbackDTO createEditFeedbackDTO = new CreateEditFeedbackDTO("EVNT00047114", "Not Participated",
				questionAnswerDTO, "Multiple Choice One answer");
		Query query = new Query();
		query.addCriteria(Criteria.where("eventId").is(createEditFeedbackDTO.getEventId()).and("feedbackType")
				.is(createEditFeedbackDTO.getFeedbackType()));

		FeedbackQuestionCollection feedbackQuestionCollection = new FeedbackQuestionCollection("EVNT00047114",
				"Not Participated", "Multiple Choice One answer", questionAnswerDTO);
		when(createEditFeedbackFormRepository.save(feedbackQuestionCollection)).thenReturn(feedbackQuestionCollection);

		FeedbackQuestionCollection actual = createEditFeedbackFormService.deleteFeedbackForm(createEditFeedbackDTO);

		verify(mongoTemplate, times(1)).findAndRemove(query, FeedbackQuestionCollection.class);
	}

}
