package com.feedback.management.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.management.collections.NotParticipatedFeedbackCollection;
import com.feedback.management.collections.ParticpatedFeedbackCollection;
import com.feedback.management.collections.UnregisteredFeedbackCollection;
import com.feedback.management.dto.NotParticipatedFeedbackDTO;
import com.feedback.management.dto.ParticipatedFeedbackDTO;
import com.feedback.management.dto.UnregisteredFeedbackDTO;
import com.feedback.management.services.ProcessFeedbackService;

@RestController
@RequestMapping("feedback")
public class ProcessFeedbackController {

	@Autowired
	private ProcessFeedbackService processFeedbackService;

	@PostMapping("participated")
	public ResponseEntity<ParticpatedFeedbackCollection> processParticpatedFeedback(
			@RequestBody ParticipatedFeedbackDTO participatedFeedbackDTO) {
		ParticpatedFeedbackCollection particpatedFeedbackCollection = processFeedbackService
				.processParticpatedFeedback(participatedFeedbackDTO);
		return Optional.ofNullable(particpatedFeedbackCollection)
				.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("unregistered")
	public ResponseEntity<UnregisteredFeedbackCollection> processUnregisteredFeedback(
			@RequestBody UnregisteredFeedbackDTO unregisteredFeedbackDTO) {
		UnregisteredFeedbackCollection unregisteredFeedbackCollection = processFeedbackService
				.processUnregisteredFeedback(unregisteredFeedbackDTO);
		return Optional.ofNullable(unregisteredFeedbackCollection)
				.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("notparticipated")
	public ResponseEntity<NotParticipatedFeedbackCollection> processNotParticpatedFeedback(
			@RequestBody NotParticipatedFeedbackDTO notParticipatedFeedbackDTO) {
		NotParticipatedFeedbackCollection notParticipatedFeedbackCollection = processFeedbackService
				.processNotParticpatedFeedback(notParticipatedFeedbackDTO);
		return Optional.ofNullable(notParticipatedFeedbackCollection)
				.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

}
