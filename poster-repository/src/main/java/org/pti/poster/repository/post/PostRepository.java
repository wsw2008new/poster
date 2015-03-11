package org.pti.poster.repository.post;


import org.pti.poster.model.post.Post;

public interface PostRepository {

	public Post getPostById(String id);

	public Post savePost(Post post);
}
