package com.feedback.management.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserCredentialsDTO implements Serializable {

	public UserCredentialsDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2494484968376756899L;
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
