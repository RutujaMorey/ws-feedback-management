package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.EventReportCollections;

@Repository
public interface EventSummaryRepository extends MongoRepository<EventReportCollections, String> {
	EventReportCollections findByEventId(String eventId);

}
