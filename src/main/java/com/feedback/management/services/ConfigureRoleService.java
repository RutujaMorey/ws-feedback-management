package com.feedback.management.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.feedback.management.collections.EventRoleRelationCollection;
import com.feedback.management.collections.UserCredentialsCollections;
import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.ConfigureRoleDTO;
import com.feedback.management.dto.SendTextResponseDTO;
import com.feedback.management.repositories.EventRoleRelationRepository;
import com.feedback.management.repositories.UserAuthenticationRepository;
import com.feedback.management.repositories.UserDetailsRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfigureRoleService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserAuthenticationRepository userAuthenticationRepository;

	@Autowired
	private EventRoleRelationRepository eventPOCRelationRepository;

	public SendTextResponseDTO configureAddRole(ConfigureRoleDTO configureRoleDTO) {
		UserDetailsCollections userDetailsCollections = userDetailsRepository
				.findByEmailAddress(configureRoleDTO.getEmailAddress());
		UserCredentialsCollections userCredentialsCollections = userAuthenticationRepository
				.findByEmail(configureRoleDTO.getEmailAddress());
		if (Objects.nonNull(userDetailsCollections) && Objects.nonNull(userCredentialsCollections)) {

			// userDetailsCollections.setRole(configureRoleDTO.getRole());
			// userCredentialsCollections.setRole(configureRoleDTO.getRole());
			// userDetailsRepository.save(userDetailsCollections);
			// userAuthenticationRepository.delete(userCredentialsCollections);
			// userAuthenticationRepository.save(userCredentialsCollectionsCopy);

			Query query1 = new Query(Criteria.where("emailAddress").is(configureRoleDTO.getEmailAddress()));
			Update update1 = new Update();
			update1.set("role", configureRoleDTO.getRole());
			mongoTemplate.updateFirst(query1, update1, UserDetailsCollections.class);
			Query query2 = new Query(Criteria.where("email").is(configureRoleDTO.getEmailAddress()));
			update1.set("role", configureRoleDTO.getRole());
			mongoTemplate.updateFirst(query2, update1, UserCredentialsCollections.class);
			EventRoleRelationCollection eventPOCRelationCollection = new EventRoleRelationCollection(
					configureRoleDTO.getEventId(), userDetailsCollections.getEmployeeID(), configureRoleDTO.getRole());

			eventPOCRelationRepository.save(eventPOCRelationCollection);
			return new SendTextResponseDTO(configureRoleDTO.getEmailAddress() + " added successfully  as "
					+ configureRoleDTO.getRole() + " for event " + configureRoleDTO.getEventId());

		}
		return new SendTextResponseDTO("");

	}

	public SendTextResponseDTO configureDeleteRole(ConfigureRoleDTO configureRoleDTO) {
		UserDetailsCollections userDetailsCollections = userDetailsRepository
				.findByEmailAddress(configureRoleDTO.getEmailAddress());
		String employeeId = userDetailsCollections.getEmployeeID();
		UserCredentialsCollections userCredentialsCollections = userAuthenticationRepository
				.findByEmail(configureRoleDTO.getEmailAddress());
		if (Objects.nonNull(userDetailsCollections) && Objects.nonNull(userCredentialsCollections)) {

			Query query1 = new Query(Criteria.where("emailAddress").is(configureRoleDTO.getEmailAddress()));
			Update update1 = new Update();
			update1.set("role", "Participant");
			mongoTemplate.updateFirst(query1, update1, UserDetailsCollections.class);
			Query query2 = new Query(Criteria.where("email").is(configureRoleDTO.getEmailAddress()));
			update1.set("role", "Participant");
			mongoTemplate.updateFirst(query2, update1, UserCredentialsCollections.class);

			Query query3 = new Query();
			query3.addCriteria(Criteria.where("employeeId").is(employeeId).and("eventId")
					.is(configureRoleDTO.getEventId()).and("role").is(configureRoleDTO.getRole()));

			EventRoleRelationCollection eventPOCRelationCollection = mongoTemplate.findOne(query3,
					EventRoleRelationCollection.class);
			mongoTemplate.remove(eventPOCRelationCollection);

			return new SendTextResponseDTO(configureRoleDTO.getEmailAddress() + " revoked as "
					+ configureRoleDTO.getRole() + " for event " + configureRoleDTO.getEventId());

		}
		return new SendTextResponseDTO("");

	}

	public SendTextResponseDTO getEventForRoleAndEmail(String email, String role) {

		UserDetailsCollections userDetailsCollections = userDetailsRepository.findByEmailAddress(email);

		return new SendTextResponseDTO(eventPOCRelationRepository
				.findByEmployeeIdAndRole(userDetailsCollections.getEmployeeID(), role).getEventId());

	}

	public List<UserDetailsCollections> getEmployeeByRoleAndEvent(String role, String eventId) {
		List<UserDetailsCollections> userDetailsCollections = new ArrayList<>();
		List<EventRoleRelationCollection> eventRoleRelationCollections = eventPOCRelationRepository
				.findByEventIdAndRole(eventId, role);
		List<String> employeeIds = eventRoleRelationCollections.stream().map(EventRoleRelationCollection::getEmployeeId)
				.distinct().collect(Collectors.toList());
		employeeIds.stream().forEach(employee -> {
			Query query = new Query(Criteria.where("_id").is(employee));
			UserDetailsCollections user = mongoTemplate.findOne(query, UserDetailsCollections.class);
			userDetailsCollections.add(user);
		});
		return userDetailsCollections;
	}

}
