package org.pti.poster.model.post;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

public class BasicPost implements Post {

	public final static PostType TYPE=PostType.BASIC_POST;

	@Getter
	private String id;
	@Getter
	private DateTime date;
	@Getter
	@Setter
	private String text;

	public BasicPost(String id, String text) {
		this.id=id;
		this.text = text;
		this.date = new DateTime();
	}
}
