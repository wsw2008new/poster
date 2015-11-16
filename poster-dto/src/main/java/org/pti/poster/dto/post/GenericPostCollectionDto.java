package org.pti.poster.dto.post;

import lombok.Getter;
import org.pti.poster.dto.AbstractDtoEntity;

import java.util.ArrayList;
import java.util.List;

public class GenericPostCollectionDto implements AbstractDtoEntity {

	@Getter
	private List<GenericPostDto> posts;

	@Getter
	protected List<String> errorMessages;

	public GenericPostCollectionDto() {
		errorMessages = new ArrayList<>();
	}

	public GenericPostCollectionDto(List<GenericPostDto> posts) {
		this.posts = posts;
	}

}
