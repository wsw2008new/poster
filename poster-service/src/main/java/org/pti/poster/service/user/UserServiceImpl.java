package org.pti.poster.service.user;

import org.pti.poster.assembler.GenericUserAssembler;
import org.pti.poster.dto.user.GenericUserDto;
import org.pti.poster.model.user.GenericUser;
import org.pti.poster.model.user.GenericUserType;
import org.pti.poster.repository.user.UserRepository;
import org.pti.poster.repository.user.UserRepositoryFactory;
import org.pti.poster.repository.user.UserRepositoryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositoryFactory userRepositoryFactory;
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		userRepository = userRepositoryFactory.getRepositoryOfType(UserRepositoryType.INMEMORY);
	}

	public GenericUserDto findUserById(String id) {
		GenericUser queryResult = userRepository.getUserById(id);
		GenericUserDto queryResultDto = null;

		try {
			queryResultDto = GenericUserAssembler.toDto(queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return queryResultDto;
	}

	public GenericUserDto createUser(GenericUserDto userDto) {
		GenericUser user = GenericUserAssembler.fromDto(userDto);
		user.setType(GenericUserType.REGISTERED_USER);
		GenericUser queryResult = userRepository.saveUser(user);
		GenericUserDto queryResultDto = null;

		try {
			queryResultDto = GenericUserAssembler.toDto(queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return queryResultDto;
	}

	@Override
	public List<GenericUserDto> getAllUsers() {
		List<GenericUser> queryResult = userRepository.getAllUsers();
		List<GenericUserDto> queryResultDto = null;
		try {
			queryResultDto = GenericUserAssembler.toDto(queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return queryResultDto;
	}
}
