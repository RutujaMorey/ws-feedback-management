package com.feedback.management.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@Document(collection = "UserSequence")
@AllArgsConstructor
public class DatabaseSequence {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	@Id
	private String id;

	private long seq;

	// getters and setters omitted
}