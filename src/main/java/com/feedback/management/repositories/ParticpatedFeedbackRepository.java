package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.ParticpatedFeedbackCollection;

@Repository
public interface ParticpatedFeedbackRepository extends MongoRepository<ParticpatedFeedbackCollection, String>{

}
