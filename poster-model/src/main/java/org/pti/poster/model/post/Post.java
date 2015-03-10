package org.pti.poster.model.post;


import org.joda.time.DateTime;

public interface Post {

	public String getId();

	public DateTime getDate();

	public void setText(String text);

	public String getText();
}
