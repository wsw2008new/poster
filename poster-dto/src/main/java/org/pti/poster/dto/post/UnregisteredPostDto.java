package org.pti.poster.dto.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.poster.model.post.GenericPostType;


public class UnregisteredPostDto extends GenericPostDto {

	public UnregisteredPostDto(String userId, String text) {
		super(GenericPostType.UNREGISTERED_POST, "", "", userId, text);
	}

	public UnregisteredPostDto(GenericPostType type, String id, String date, String userId, String text) {
		super(type, id, date, userId, text);
	}

	@JsonIgnore
	@Override
	public String getId() {
		return super.getId();
	}

	@JsonIgnore
	@Override
	public String getDate() {
		return super.getDate();
	}

}
