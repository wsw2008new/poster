package org.pti.poster.assembler;

import org.pti.poster.dto.user.GenericUserCollectionDto;
import org.pti.poster.dto.user.GenericUserDto;
import org.pti.poster.dto.user.RegisteredUserDto;
import org.pti.poster.dto.user.UnregisteredUserDto;
import org.pti.poster.model.user.AbstractUser;
import org.pti.poster.model.user.GenericUser;

import javax.inject.Singleton;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class GenericUserAssembler extends AbstractAssembler {

	public static GenericUser fromDto(GenericUserDto userDto) {
		return convertFromDto(userDto);
	}

	public static GenericUserDto toDto(GenericUser user) {
		return convertToDto(user);
	}

	public static GenericUserCollectionDto toDto(List<GenericUser> users) {
		return convertToDto(users);
	}

	public static void copyFieldsFromTo(AbstractUser from, AbstractUser to) {
		to.setType(from.getType());
		to.setUserId(from.getUserId());
		to.setUserName(from.getUserName());
		to.setUserNickName(from.getUserNickName());
	}

	private static GenericUserCollectionDto convertToDto(List<GenericUser> users) {
		GenericUserCollectionDto result = new GenericUserCollectionDto();

		if (users != null && !users.isEmpty()) {
			for (GenericUser user : users) {
				result.getUsers().add(toDto(user));
			}
		}

		return result;
	}

	private static GenericUserDto convertToDto(GenericUser user) {
		String className;

		if (user == null) {
			return null;
		}

		switch (user.getType()) {
			case REGISTERED_USER:
				className = RegisteredUserDto.class.getName();
				break;
			case UNREGISTERED_USER:
				className = UnregisteredUserDto.class.getName();
				break;
			case NEW_USER:
				className = UnregisteredUserDto.class.getName();
				break;
			default:
				className = "";
				break;
		}

		GenericUserDto userDto = (GenericUserDto) getNewInstanceFor(className);
		copyFieldsFromTo(user, userDto);

		return userDto;
	}

	private static GenericUser convertFromDto(GenericUserDto userDto) {
		GenericUser user = new GenericUser();
		copyFieldsFromTo(userDto, user);

		return user;
	}

}
