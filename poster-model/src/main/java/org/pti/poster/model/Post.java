package org.pti.poster.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Post implements Serializable {
	private static final long serialVersionUID = -6672089623741166605L;

	public Post(String text) {
		this.id = UUID.randomUUID().toString();
		this.text = text;
	}

	@Getter
	private final String id;

	@Getter
	@Setter
	private String text;
}
