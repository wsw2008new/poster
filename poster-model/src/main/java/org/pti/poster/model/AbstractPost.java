package org.pti.poster.model;

import lombok.Data;
import org.pti.poster.model.post.GenericPostType;

@Data
public abstract class AbstractPost {

	public final static String DATE_FORMAT = "dd.MM.yyyy";

	protected GenericPostType type;

	protected String id;

	protected String date;

	protected String text;

	protected String userId;

	public AbstractPost(GenericPostType type, String id, String date, String userId, String text) {
		this.type = type;
		this.id = id;
		this.date = date;
		this.userId = userId;
		this.text = text;
	}

}
