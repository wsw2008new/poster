package org.pti.poster.model.post;


import org.joda.time.DateTime;

public interface Post {

	public PostType getType();

	public String getId();

	public DateTime getDate();

	public String getText();
}
