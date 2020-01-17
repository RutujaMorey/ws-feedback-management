package com.feedback.management.dto;

import java.io.Serializable;

import lombok.Data;

@Data

public class UserDetailsDTO implements Serializable {

	public UserDetailsDTO(String employeeID, String firstName, String lastName, String emailAddress,
			String contactNumber, String role, String password) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.role = role;
		this.password = password;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5773444831702146496L;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String employeeID;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contactNumber;
	private String role;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
