package com.feedback.management.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.management.collections.UserDetailsCollections;
import com.feedback.management.dto.ConfigureRoleDTO;
import com.feedback.management.dto.SendTextResponseDTO;
import com.feedback.management.services.ConfigureRoleService;

@RestController
@RequestMapping("/config")
public class ConfigureRolesController {

	@Autowired
	private ConfigureRoleService configureRoleService;

	@PostMapping("/add")
	public ResponseEntity<SendTextResponseDTO> configureAddRole(@RequestBody ConfigureRoleDTO configureRoleDTO) {
		SendTextResponseDTO revokeStatus = configureRoleService.configureAddRole(configureRoleDTO);
		return Optional.ofNullable(revokeStatus).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@PostMapping("/delete")
	public ResponseEntity<SendTextResponseDTO> configureDeleteRole(@RequestBody ConfigureRoleDTO configureRoleDTO) {
		SendTextResponseDTO revokeStatus = configureRoleService.configureDeleteRole(configureRoleDTO);
		return Optional.ofNullable(revokeStatus).map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@GetMapping("/fetcheventfor/{emailId}/{role}")
	public ResponseEntity<SendTextResponseDTO> getEventForRoleAndEmail(@PathVariable("emailId") String emailId,
			@PathVariable("role") String role) {
		SendTextResponseDTO event = configureRoleService.getEventForRoleAndEmail(emailId, role);
		return Objects.nonNull(event) ? new ResponseEntity<>(event, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/fetchemployeesfor/{eventId}/{role}")
	public ResponseEntity<List<UserDetailsCollections>> getEmployeesForeventAndRole(
			@PathVariable("eventId") String eventId, @PathVariable("role") String role) {
		List<UserDetailsCollections> userDetailsCollections = configureRoleService.getEmployeeByRoleAndEvent(eventId,
				role);
		return CollectionUtils.isEmpty(userDetailsCollections) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(userDetailsCollections, HttpStatus.OK);
	}

}
