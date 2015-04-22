package org.pti.poster.dto.post;

import java.util.List;

public class GenericPostCollectionDto {

	private List<GenericPostDto> posts;

	public GenericPostCollectionDto(List<GenericPostDto> posts) {
		this.posts = posts;
	}

	public List<GenericPostDto> getPosts() {
		return posts;
	}

}
