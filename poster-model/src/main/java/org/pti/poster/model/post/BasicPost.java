package org.pti.poster.model.post;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

public class BasicPost implements Post {

	@Getter
	private String id;
	@Getter
	private DateTime date;
	@Getter
	private String text;

	public BasicPost(String text){
		this.text=text;
		this.date=new DateTime();
	}

	@Override
	public void setText() {

	}
}
