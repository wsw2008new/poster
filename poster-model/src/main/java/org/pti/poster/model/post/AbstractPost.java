package org.pti.poster.model.post;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@JsonFilter("postFilter")
public abstract class AbstractPost implements Post {

	@Getter
	private PostType type;
	@Getter
	private String id;
	@Getter
	protected String date;
	@Getter
	protected String text;

	public AbstractPost(PostType type, String id, String text) {
		this.type = type;
		this.id = id;
		this.text = text;

		setDate();
	}

	private void setDate() {
		DateTime date = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
		this.date = date.toString(fmt);
	}

	@Override
	public String toString() {
		return "#" + id + ": " + text;
	}

}
