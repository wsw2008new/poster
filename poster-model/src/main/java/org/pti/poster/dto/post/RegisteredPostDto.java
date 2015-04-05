package org.pti.poster.dto.post;

import org.pti.poster.model.post.GenericPostType;

public class RegisteredPostDto extends GenericPostDto {

	public RegisteredPostDto(GenericPostType type, String id, String date, String userId, String text) {
		super(type, id, date, userId, text);
	}
}
