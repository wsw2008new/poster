package org.pti.poster.service.post;

import org.pti.poster.model.post.Post;
import org.pti.poster.model.post.PostCollection;
import org.pti.poster.repository.post.PostRepository;
import org.pti.poster.repository.post.PostRepositoryFactory;
import org.pti.poster.repository.post.PostRepositoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service("PostService")
public class PostService {

	@Autowired
	private PostRepositoryFactory postRepositoryFactory;
	private PostRepository postRepository;

	@PostConstruct
	public void init() {
		postRepository = postRepositoryFactory.getRepositoryOfType(PostRepositoryType.INMEMORY);
	}

	public Post findPostById(String id) {
		return postRepository.getPostById(id);
	}

	public PostCollection getLastPosts(int number) {
		return postRepository.getLastPosts(number);
	}

	public Post savePost(Post post) {
		return postRepository.savePost(post);
	}
}
