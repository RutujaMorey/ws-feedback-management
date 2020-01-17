package com.feedback.management.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.EventRoleRelationCollection;

@Repository
public interface EventRoleRelationRepository extends MongoRepository<EventRoleRelationCollection, String> {
	EventRoleRelationCollection findByEmployeeId(String employeeId);

	EventRoleRelationCollection findByEmployeeIdAndRole(String employeeId, String role);

	@Query(value = "{ 'eventId' : ?0, 'role': ?1}")
	List<EventRoleRelationCollection> findByEventIdAndRole(String eventId, String role);

}
