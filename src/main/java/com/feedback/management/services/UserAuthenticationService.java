package com.feedback.management.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.feedback.management.collections.DatabaseSequence;
import com.feedback.management.collections.UserCredentialsCollections;
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.UserCredentialsDTO;
import com.feedback.management.dto.UserDetailsDTO;
import com.feedback.management.repositories.UserAuthenticationRepository;
import com.feedback.management.repositories.UserDetailsRepository;

@Service
public class UserAuthenticationService {

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	private static final String HOSTING_SEQ_KEY = "hosting";

	@Autowired
	private MongoOperations mongoOperation;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public UserDetailsCollections authenticateUser(UserCredentialsDTO userCredentialsDTO) {
		UserCredentialsCollections userCredentials = userAuthenticationRepository
				.findByEmail(userCredentialsDTO.getEmail());
		if (Objects.nonNull(userCredentials)) {
			if (userCredentials.getPassword().equals(userCredentialsDTO.getPassword())) {
				return userDetailsRepository.findByEmailAddress(userCredentials.getEmail());
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public UserDetailsCollections createUser(UserDetailsDTO userDetailsDTO) {
		String employeeId = "";
		try {
			employeeId = String.valueOf(generateSequence(HOSTING_SEQ_KEY));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDetailsCollections userDetailsCollection = new UserDetailsCollections(userDetailsDTO.getFirstName(),
				userDetailsDTO.getLastName(), userDetailsDTO.getEmailAddress(), userDetailsDTO.getContactNumber(),
				userDetailsDTO.getRole(), employeeId);
		UserCredentialsCollections userCredentialsCollections = new UserCredentialsCollections(
				userDetailsDTO.getEmailAddress(), userDetailsDTO.getPassword(), userDetailsDTO.getRole());
		userAuthenticationRepository.save(userCredentialsCollections);
		return userDetailsRepository.save(userDetailsCollection);

	}

	public long generateSequence(String key) throws Exception {

		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		DatabaseSequence seqId = mongoOperation.findAndModify(query, update, options, DatabaseSequence.class);

		if (seqId == null) {
			throw new Exception("Unable to get sequence id for key : " + key);
		}

		return seqId.getSeq();

	}

}
