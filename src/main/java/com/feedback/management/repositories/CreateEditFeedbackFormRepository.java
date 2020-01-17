package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.FeedbackQuestionCollection;

@Repository
public interface CreateEditFeedbackFormRepository extends MongoRepository<FeedbackQuestionCollection, String> {

}
