package com.feedback.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.feedback.management.collections.FeedbackQuestionCollection;
import com.feedback.management.dto.CreateEditFeedbackDTO;
import com.feedback.management.repositories.CreateEditFeedbackFormRepository;

@Service
public class CreateEditFeedbackFormService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired 
	private CreateEditFeedbackFormRepository createEditFeedbackFormRepository;

	public FeedbackQuestionCollection createFeedbackForm(CreateEditFeedbackDTO createEditFeedbackDTO) {
		FeedbackQuestionCollection feedbackQuestionCollection = new FeedbackQuestionCollection(
				createEditFeedbackDTO.getEventId(), createEditFeedbackDTO.getFeedbackType(),
				createEditFeedbackDTO.getQuestionType(), createEditFeedbackDTO.getQuestionAnswer());
		return createEditFeedbackFormRepository.save(feedbackQuestionCollection);

	}

	public FeedbackQuestionCollection editFeedbackForm(CreateEditFeedbackDTO createEditFeedbackDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("eventId").is(createEditFeedbackDTO.getEventId()).and("feedbackType")
				.is(createEditFeedbackDTO.getFeedbackType()));
		Update update = new Update();
	
		update.set("questionType", createEditFeedbackDTO.getQuestionType());
		update.set("questionAnswers", createEditFeedbackDTO.getQuestionAnswer());

		mongoTemplate.updateFirst(query, update, FeedbackQuestionCollection.class);
		return mongoTemplate.findOne(query, FeedbackQuestionCollection.class);

	}

	public FeedbackQuestionCollection getFeedbackForm(CreateEditFeedbackDTO createEditFeedbackDTO) {
		Query query = new Query();
		query.addCriteria(Criteria.where("eventId").is(createEditFeedbackDTO.getEventId()).and("feedbackType")
				.is(createEditFeedbackDTO.getFeedbackType()));
		return mongoTemplate.findOne(query, FeedbackQuestionCollection.class);

	}

	public List<FeedbackQuestionCollection> getAllFeedbackForm() {

		return createEditFeedbackFormRepository.findAll();

	}
	
	public FeedbackQuestionCollection  deleteFeedbackForm(CreateEditFeedbackDTO createEditFeedbackDTO) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("eventId").is(createEditFeedbackDTO.getEventId()).and("feedbackType")
				.is(createEditFeedbackDTO.getFeedbackType()));
		return mongoTemplate.findAndRemove(query, FeedbackQuestionCollection.class);
	
	}

}
