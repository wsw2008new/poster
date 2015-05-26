package org.pti.poster.repository.post;

import org.pti.poster.model.post.GenericPost;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository("inMemoryPostRepository")
public class InMemoryPostRepositoryImpl implements InMemoryPostRepository {

	private Map<String, GenericPost> allPosts;

	@PostConstruct
	public void init() {
		allPosts = new LinkedHashMap<>();
	}

	@Override
	public GenericPost getPostById(String id) {
		return allPosts.get(id);
	}

	@Override
	public List<GenericPost> getLastPosts(int number) {
		List<GenericPost> result = new ArrayList<>();
		List<Map.Entry<String, GenericPost>> entryList = new ArrayList<>(allPosts.entrySet());

		int endIndex = entryList.size();
		int startIndex = endIndex - number;

		List<Map.Entry<String, GenericPost>> lastEntries = entryList.subList(startIndex, endIndex);
		for (Map.Entry<String, GenericPost> entry : lastEntries) {
			result.add(entry.getValue());
		}

		return result;
	}

	@Override
	public GenericPost savePost(GenericPost post) {
		GenericPost savedPost = new GenericPost();
		String id = UUID.randomUUID().toString();

		savedPost.setId(id);
		savedPost.setType(post.getType());
		savedPost.setText(post.getText());
		savedPost.setUserId(post.getUserId());
		savedPost.setDate(post.getDate());

		allPosts.put(id, savedPost);
		return savedPost;
	}
}
