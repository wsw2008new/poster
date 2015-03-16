package org.pti.poster.repository.post;


import org.pti.poster.model.post.Post;
import org.pti.poster.model.post.PostCollection;

public interface PostRepository {

	public Post getPostById(String id);

	public PostCollection getLastPosts(int number);

	public Post savePost(Post post);
}
