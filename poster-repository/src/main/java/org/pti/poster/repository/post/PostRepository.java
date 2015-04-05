package org.pti.poster.repository.post;


import org.pti.poster.model.post.AbstractPost;
import org.pti.poster.model.post.PostCollection;

public interface PostRepository {

	public AbstractPost getPostById(String id);

	public PostCollection getLastPosts(int number);

	public AbstractPost savePost(AbstractPost post);
}
