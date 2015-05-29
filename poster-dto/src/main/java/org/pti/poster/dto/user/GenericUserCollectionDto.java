package org.pti.poster.dto.user;

import java.util.List;

public class GenericUserCollectionDto {

	private List<GenericUserDto> users;

	public GenericUserCollectionDto(List<GenericUserDto> users) {
		this.users = users;
	}

	public List<GenericUserDto> getUsers() {
		return users;
	}

}
