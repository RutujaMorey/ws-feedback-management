package com.feedback.management.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateEditFeedbackDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7417018888329174208L;
	public String eventId;

	public String questionType;

	public String feedbackType;

	public QuestionAnswerDTO questionAnswer;

	public CreateEditFeedbackDTO() {
		super();
	}

	public CreateEditFeedbackDTO(String eventId, String feedbackType, QuestionAnswerDTO questionAnswer,
			String questionType) {
		super();
		this.eventId = eventId;
		this.feedbackType = feedbackType;
		this.questionAnswer = questionAnswer;
		this.questionType = questionType;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public QuestionAnswerDTO getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(QuestionAnswerDTO questionAnswer) {
		this.questionAnswer = questionAnswer;
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
