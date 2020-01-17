package com.feedback.management.dto;

import lombok.Data;

@Data
public class SendTextResponseDTO {

	private String response;

	public String getResponse() {
		return response;
	}

	public SendTextResponseDTO(String response) {
		super();
		this.response = response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
