package org.pti.poster.model.post;

import java.util.List;

public class PostCollection {

	private List<AbstractPost> posts;

	private PostCollection() {
	}

	public PostCollection(List<AbstractPost> posts) {
		this.posts = posts;
	}

	public List<AbstractPost> getPosts() {
		return posts;
	}

}
