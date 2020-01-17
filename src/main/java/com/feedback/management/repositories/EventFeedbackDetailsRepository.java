package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.feedback.management.collections.EventFeedBackDetailsCollections;


@Repository
public interface EventFeedbackDetailsRepository extends MongoRepository<EventFeedBackDetailsCollections, String> {
	List<EventFeedBackDetailsCollections> findByEventId(String eventId);

}
