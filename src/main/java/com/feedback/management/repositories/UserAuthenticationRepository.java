package com.feedback.management.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedback.management.collections.UserCredentialsCollections;

@Repository
public interface UserAuthenticationRepository extends MongoRepository<UserCredentialsCollections, String> {
	
	UserCredentialsCollections findByEmail(String email);
}