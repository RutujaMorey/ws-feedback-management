package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.NotParticipatedFeedbackCollection;

@Repository
public interface NotParticipatedFeedbackRepository extends MongoRepository<NotParticipatedFeedbackCollection, String>{

}
