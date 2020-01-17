package com.feedback.management.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserDetails")
public class UserDetailsCollections {
	@Override
	public String toString() {
		return "UserDetailsCollections [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName="
				+ lastName + ", emailAddress=" + emailAddress + ", contactNumber=" + contactNumber + ", role=" + role
				+ "]";
	}
	@Transient
	private static final String HOSTING_SEQ_KEY = "hosting";
	
	@Id
	private String employeeID;
	
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
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contactNumber;
	private String role;
	public UserDetailsCollections(String firstName, String lastName, String emailAddress, String contactNumber,
			String role, String employeeID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
		this.role = role;
		this.employeeID= employeeID;
	}
}
