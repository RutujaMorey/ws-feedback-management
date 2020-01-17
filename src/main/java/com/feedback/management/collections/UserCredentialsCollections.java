package com.feedback.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "UserCredentials")
public class UserCredentialsCollections {

	@Override
	public String toString() {
		return "UserCredentialsCollections [email=" + email + ", password=" + password + ", role=" + role + "]";
	}

	@JsonProperty("email")
	public String email;

	@JsonProperty("password")
	public String password;

	@JsonProperty("role")
	public String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserCredentialsCollections(String email, String password, String role) {

		this.email = email;
		this.password = password;
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

}
