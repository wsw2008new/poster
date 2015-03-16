package org.pti.poster.repository.post;


import org.pti.poster.model.post.Post;

import java.util.List;

public interface PostRepository {

	public Post getPostById(String id);

	public List<Post> getLastPosts(int number);

	public Post savePost(Post post);
}
