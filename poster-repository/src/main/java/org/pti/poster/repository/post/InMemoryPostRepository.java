package org.pti.poster.repository.post;

import org.pti.poster.model.post.Post;
import org.pti.poster.model.post.RegisteredPost;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository("inMemoryPostRepository")
public class InMemoryPostRepository implements PostRepository {

	public final static PostRepositoryType TYPE = PostRepositoryType.INMEMORY;
	Map<String, Post> allPosts;

	@PostConstruct
	public void init() {
		allPosts = new LinkedHashMap<>();
	}

	@Override
	public Post getPostById(String id) {
		return allPosts.get(id);
	}

	@Override
	public List<Post> getLastPosts(int number) {
		List<Post> result = new ArrayList<>();
		List<Map.Entry<String, Post>> entryList = new ArrayList<>(allPosts.entrySet());

		int endIndex = entryList.size();
		int startIndex = endIndex - number;

		List<Map.Entry<String, Post>> lastEntries = entryList.subList(startIndex, endIndex);
		for (Map.Entry<String, Post> entry : lastEntries) {
			result.add(entry.getValue());
		}

		return result;
	}

	@Override
	public Post savePost(Post post) {
		String id = UUID.randomUUID().toString();
		Post savedPost = new RegisteredPost(id, post.getUserId(), post.getText());
		allPosts.put(id, savedPost);

		return savedPost;
	}
}
