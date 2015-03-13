package org.pti.poster.model.post;

public class RegisteredPost extends AbstractPost {

	public final static PostType TYPE = PostType.REGISTERED_POST;

	public RegisteredPost(String id, String userId, String text) {
		super(TYPE, id, userId, text);
	}
}
