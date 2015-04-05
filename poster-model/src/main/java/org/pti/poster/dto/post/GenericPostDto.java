package org.pti.poster.dto.post;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pti.poster.model.AbstractPost;
import org.pti.poster.model.post.GenericPostType;

@EqualsAndHashCode(callSuper = false)
@Data
@JsonFilter("postFilter")
public abstract class GenericPostDto extends AbstractPost {

	public GenericPostDto(GenericPostType type, String id, String date, String userId, String text) {
		super(type, id, date, userId, text);
	}

	@JsonIgnore
	@Override
	public GenericPostType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "#" + id + ": " + text;
	}

}
