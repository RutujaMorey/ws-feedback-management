package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.UnregisteredFeedbackCollection;

@Repository
public interface UnregisteredFeedbackRepository extends MongoRepository<UnregisteredFeedbackCollection, String>{

}
