package org.pti.poster.model.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

public interface Post {

	@JsonIgnore
	public PostType getType();

	public String getId();

	public String getDate();

	public String getText();

	public String getUserId();
}
