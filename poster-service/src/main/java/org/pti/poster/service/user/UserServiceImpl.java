package org.pti.poster.service.user;

import org.pti.poster.assembler.GenericUserAssembler;
import org.pti.poster.dto.user.GenericUserCollectionDto;
import org.pti.poster.dto.user.GenericUserDto;
import org.pti.poster.model.user.GenericUser;
import org.pti.poster.model.user.GenericUserType;
import org.pti.poster.repository.user.MongoUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private MongoUserRepository userRepository;

	@Override
	public GenericUserDto findUserById(String id) {
		GenericUser queryResult = userRepository.getUserByUserId(id);
		GenericUserDto queryResultDto = null;

		try {
			queryResultDto = GenericUserAssembler.toDto(queryResult);
		} catch (Exception e) {
			LOGGER.warn("Can't find user", e);
		}

		return queryResultDto;
	}

	@Override
	public GenericUserDto createUser(GenericUserDto userDto) {
		GenericUser user = GenericUserAssembler.fromDto(userDto);
		user.setType(GenericUserType.REGISTERED_USER);
		GenericUser queryResult = userRepository.save(user);
		GenericUserDto queryResultDto = null;

		try {
			queryResultDto = GenericUserAssembler.toDto(queryResult);
		} catch (Exception e) {
			LOGGER.warn("Can't create user", e);
		}

		return queryResultDto;
	}

	@Override
	public GenericUserCollectionDto getAllUsers() {
		List<GenericUser> queryResult = userRepository.findAll();
		GenericUserCollectionDto result = null;

		try {
			result = GenericUserAssembler.toDto(queryResult);
		} catch (Exception e) {
			LOGGER.warn("No users found", e);
		}

		if (result.getUsers() == null || queryResult.isEmpty()) {
			result.getErrorMessages().add("No users found");
		}

		return result;
	}
}
