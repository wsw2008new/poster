package org.pti.poster.dto.user;

import lombok.Getter;
import org.pti.poster.dto.AbstractDtoEntity;

import java.util.ArrayList;
import java.util.List;

public class GenericUserCollectionDto implements AbstractDtoEntity {

	@Getter
	private List<GenericUserDto> users;

	@Getter
	protected List<String> errorMessages;

	public GenericUserCollectionDto() {
		users = new ArrayList<>();
		errorMessages = new ArrayList<>();
	}


}
