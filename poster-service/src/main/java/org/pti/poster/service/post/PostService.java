package org.pti.poster.service.post;

import org.pti.poster.model.post.Post;

public interface PostService {

	public Post findPostById(String id);

	public Post savePost(Post post);
}
