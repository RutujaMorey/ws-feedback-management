package com.feedback.management.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.management.collections.FeedbackQuestionCollection;
import com.feedback.management.dto.CreateEditFeedbackDTO;
import com.feedback.management.services.CreateEditFeedbackFormService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/forms")
@AllArgsConstructor
public class CreateEditFeedbackFormsController {

	@Autowired
	private CreateEditFeedbackFormService createEditFeedbackFormService;

	@PostMapping("/create")
	public ResponseEntity<FeedbackQuestionCollection> createFeedbackForm(
			@RequestBody CreateEditFeedbackDTO createEditFeedbackDTO) {
		FeedbackQuestionCollection feedbackQuestionCollection = createEditFeedbackFormService
				.createFeedbackForm(createEditFeedbackDTO);
		return Optional.ofNullable(feedbackQuestionCollection)
				.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

	}

	@PostMapping("/getform")
	public ResponseEntity<FeedbackQuestionCollection> getFeedbackForm(
			@RequestBody CreateEditFeedbackDTO createEditFeedbackDTO) {
		FeedbackQuestionCollection feedbackQuestionCollection = createEditFeedbackFormService
				.getFeedbackForm(createEditFeedbackDTO);
		return Optional.ofNullable(feedbackQuestionCollection).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@PatchMapping("/edit")
	public ResponseEntity<FeedbackQuestionCollection> editFeedbackForm(
			@RequestBody CreateEditFeedbackDTO createEditFeedbackDTO) {
		FeedbackQuestionCollection feedbackQuestionCollection = createEditFeedbackFormService
				.editFeedbackForm(createEditFeedbackDTO);
		return Objects.nonNull(feedbackQuestionCollection)
				? new ResponseEntity<>(feedbackQuestionCollection, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getallform")
	public ResponseEntity<List<FeedbackQuestionCollection>> getAllFeedbackForm() {
		List<FeedbackQuestionCollection> feedbackQuestionCollections = createEditFeedbackFormService
				.getAllFeedbackForm();

		return CollectionUtils.isEmpty(feedbackQuestionCollections) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(feedbackQuestionCollections, HttpStatus.OK);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<FeedbackQuestionCollection> deleteFeedbackForm(
			@RequestBody CreateEditFeedbackDTO createEditFeedbackDTO) {
		FeedbackQuestionCollection feedbackQuestionCollection = createEditFeedbackFormService
				.deleteFeedbackForm(createEditFeedbackDTO);
		return Optional.ofNullable(feedbackQuestionCollection)
				.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

	}

}
