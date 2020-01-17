package com.feedback.management.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.UserDetailsCollections;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetailsCollections, String> {

	UserDetailsCollections findByEmailAddress(String emailAddress);
	
	List<UserDetailsCollections> findByRole(String role);

}
