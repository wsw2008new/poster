package org.pti.poster.repository.post;

import org.pti.poster.model.post.AbstractPost;
import org.pti.poster.model.post.PostCollection;
import org.pti.poster.model.post.RegisteredPost;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository("inMemoryPostRepository")
public class InMemoryPostRepository implements PostRepository {

	public final static PostRepositoryType TYPE = PostRepositoryType.INMEMORY;
	Map<String, AbstractPost> allPosts;

	@PostConstruct
	public void init() {
		allPosts = new LinkedHashMap<>();
	}

	@Override
	public AbstractPost getPostById(String id) {
		return allPosts.get(id);
	}

	@Override
	public PostCollection getLastPosts(int number) {
		List<AbstractPost> result = new ArrayList<>();
		List<Map.Entry<String, AbstractPost>> entryList = new ArrayList<>(allPosts.entrySet());

		int endIndex = entryList.size();
		int startIndex = endIndex - number;

		List<Map.Entry<String, AbstractPost>> lastEntries = entryList.subList(startIndex, endIndex);
		for (Map.Entry<String, AbstractPost> entry : lastEntries) {
			result.add(entry.getValue());
		}

		return new PostCollection(result);
	}

	@Override
	public AbstractPost savePost(AbstractPost post) {
		String id = UUID.randomUUID().toString();
		AbstractPost savedPost = new RegisteredPost(id, post.getUserId(), post.getText());
		allPosts.put(id, savedPost);

		return savedPost;
	}
}
