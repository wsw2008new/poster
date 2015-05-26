package org.pti.poster.service.post;

import org.pti.poster.dto.post.GenericPostDto;

public interface PostService {

	GenericPostDto findPostById(String id);

	GenericPostDto savePost(GenericPostDto newPostDto);

}
