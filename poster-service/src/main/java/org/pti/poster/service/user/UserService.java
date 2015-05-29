package org.pti.poster.service.user;

import org.pti.poster.dto.user.GenericUserCollectionDto;
import org.pti.poster.dto.user.GenericUserDto;

import java.util.List;

public interface UserService {

	GenericUserDto findUserById(String id);

	GenericUserDto createUser(GenericUserDto userDto);

	GenericUserCollectionDto getAllUsers();
}
