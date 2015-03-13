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

	private final static String DATE_FORMAT = "dd.MM.yyyy";

	@Getter
	private PostType type;
	@Getter
	private String id;
	@Getter
	protected String date;
	@Getter
	protected String text;
	@Getter
	protected String userId;

	public AbstractPost(PostType type, String id, String userId, String text) {
		this.type = type;
		this.id = id;
		this.userId=userId;
		this.text = text;

		setDate();
	}

	private void setDate() {
		DateTime date = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_FORMAT);
		this.date = date.toString(fmt);
	}

	@Override
	public String toString() {
		return "#" + id + ": " + text;
	}

}
