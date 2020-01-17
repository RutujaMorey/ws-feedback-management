package com.feedback.management.collections;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.feedback.management.dto.QuestionAnswerDTO;

@Document(collection = "FeedbackQuestion")
public class FeedbackQuestionCollection {

	@JsonProperty("eventId")
	private String eventId;

	@JsonProperty("feedbackType")
	private String feedbackType;

	@JsonProperty("questionType")
	private String questionType;

	@JsonProperty("questionAnswers")
	private QuestionAnswerDTO questionAnswers;

	public FeedbackQuestionCollection(String eventId, String feedbackType, String questionType,
			QuestionAnswerDTO questionAnswers) {
		super();
		this.eventId = eventId;
		this.feedbackType = feedbackType;
		this.questionType = questionType;
		this.questionAnswers = questionAnswers;
	}

	public QuestionAnswerDTO getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(QuestionAnswerDTO questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

}
