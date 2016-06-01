package org.pti.poster.service.post;

import org.pti.poster.dto.post.GenericPostCollectionDto;
import org.pti.poster.dto.post.GenericPostDto;

public interface PostService {

	GenericPostDto findPostById(String id);

	GenericPostCollectionDto findPostsByUserId(String id);

	GenericPostDto savePost(GenericPostDto newPostDto);

	void deletePost(String id);
}
