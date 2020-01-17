package com.feedback.management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.management.collections.NotParticipatedFeedbackCollection;
import com.feedback.management.collections.ParticpatedFeedbackCollection;
import com.feedback.management.collections.UnregisteredFeedbackCollection;
import com.feedback.management.dto.NotParticipatedFeedbackDTO;
import com.feedback.management.dto.ParticipatedFeedbackDTO;
import com.feedback.management.dto.UnregisteredFeedbackDTO;
import com.feedback.management.repositories.NotParticipatedFeedbackRepository;
import com.feedback.management.repositories.ParticpatedFeedbackRepository;
import com.feedback.management.repositories.UnregisteredFeedbackRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProcessFeedbackService {

	@Autowired
	private ParticpatedFeedbackRepository particpatedFeedbackRepository;

	@Autowired
	private NotParticipatedFeedbackRepository notParticipatedFeedbackRepository;

	@Autowired
	private UnregisteredFeedbackRepository unregisteredFeedbackRepository;

	public ParticpatedFeedbackCollection processParticpatedFeedback(ParticipatedFeedbackDTO participatedFeedbackDTO) {
		ParticpatedFeedbackCollection particpatedFeedbackCollection = new ParticpatedFeedbackCollection(
				participatedFeedbackDTO.getEventId(), participatedFeedbackDTO.getEmail(),
				participatedFeedbackDTO.getRating(), participatedFeedbackDTO.getImprovements(),
				participatedFeedbackDTO.getLikes());
		return particpatedFeedbackRepository.save(particpatedFeedbackCollection);

	}

	public NotParticipatedFeedbackCollection processNotParticpatedFeedback(
			NotParticipatedFeedbackDTO notParticipatedFeedbackDTO) {
		NotParticipatedFeedbackCollection notParticipatedFeedbackCollection = new NotParticipatedFeedbackCollection(
				notParticipatedFeedbackDTO.getEventId(), notParticipatedFeedbackDTO.getEmail(),
				notParticipatedFeedbackDTO.getNotParticpatedReason());
		return notParticipatedFeedbackRepository.save(notParticipatedFeedbackCollection);

	}

	public UnregisteredFeedbackCollection processUnregisteredFeedback(UnregisteredFeedbackDTO unregisteredFeedbackDTO) {
		UnregisteredFeedbackCollection unregisteredFeedbackCollection = new UnregisteredFeedbackCollection(
				unregisteredFeedbackDTO.getEventId(), unregisteredFeedbackDTO.getEmail(),
				unregisteredFeedbackDTO.getUnregisteredReason());
		return unregisteredFeedbackRepository.save(unregisteredFeedbackCollection);

	}

}
