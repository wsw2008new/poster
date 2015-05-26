package org.pti.poster.model.post;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
public abstract class AbstractPost implements Serializable {

	protected GenericPostType type;

	@Id
	protected String id;

	protected String date;

	protected String text;

	protected String userId;

	public AbstractPost() {
	}

	public AbstractPost(GenericPostType type, String id, String userId, String text) {
		this.type = type;
		this.id = id;
		this.userId = userId;
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AbstractPost that = (AbstractPost) o;

		if (!id.equals(that.id)) return false;
		if (!date.equals(that.date)) return false;
		return userId.equals(that.userId);

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + date.hashCode();
		result = 31 * result + userId.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "#" + id + " | " + userId + " | " + text;
	}
}
