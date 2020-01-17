package com.feedback.management.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class QuestionAnswerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2935422506069085017L;

	public String question;
	public List<String> answers;

	

	public QuestionAnswerDTO(String question, List<String> answers) {
		
		this.question = question;
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

}
