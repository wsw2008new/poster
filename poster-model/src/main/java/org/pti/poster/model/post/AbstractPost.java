package org.pti.poster.model.post;

import lombok.Getter;
import org.codehaus.jackson.map.annotate.JsonFilter;
import org.joda.time.DateTime;

@JsonFilter("postFilter")
public abstract class AbstractPost implements Post {

	@Getter
	private PostType type;
	@Getter
	private String id;
	@Getter
	protected DateTime date;
	@Getter
	protected String text;

	public AbstractPost(PostType type, String id, String text){
		this.type=type;
		this.id=id;
		this.text=text;
		this.date = new DateTime();
	}

	@Override
	public String toString() {
		return "#" + id + ": " + text;
	}

}
