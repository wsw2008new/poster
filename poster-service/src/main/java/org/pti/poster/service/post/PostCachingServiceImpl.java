package org.pti.poster.service.post;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.util.QueryResultSet;
import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;
import org.pti.poster.hazelcast.HazelcastConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service("postCachingService")
public class PostCachingServiceImpl implements PostCachingService {

	@Autowired
	private HazelcastConfiguration hazelcastConfiguration;
	private HazelcastInstance hazelcastInstance;

	@PostConstruct
	public void init() {
		hazelcastInstance = hazelcastConfiguration.getHazelcastInstance();
	}

	@Override
	public void cachePost(GenericPostDto newPostDto) {
		setPostDtoRandomId(newPostDto);
		hazelcastInstance.getMap("posts").put(newPostDto.getId(), newPostDto);
	}

	@Override
	public GenericPostCollectionDto getCachedPosts() {
		return getGenericPostCollectionDtoFrom((QueryResultSet) hazelcastInstance.getMap("posts").values());
	}

	private static void setPostDtoRandomId(GenericPostDto post) {
		post.setId(UUID.randomUUID().toString());
	}

	private static GenericPostCollectionDto getGenericPostCollectionDtoFrom(QueryResultSet set) {
		GenericPostCollectionDto result = new GenericPostCollectionDto();
		for (Object obj : set.toArray()) {
			result.getPosts().add((GenericPostDto) obj);
		}
		return result;
	}
}
