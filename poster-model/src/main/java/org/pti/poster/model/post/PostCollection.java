package org.pti.poster.model.post;

import java.util.List;

public class PostCollection {

	private List<Post> posts;

	private PostCollection() {
	}

	public PostCollection(List<Post> posts) {
		this.posts = posts;
	}

	public List<Post> getPosts() {
		return posts;
	}

}
