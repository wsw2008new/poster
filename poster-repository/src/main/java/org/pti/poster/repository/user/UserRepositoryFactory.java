package org.pti.poster.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userRepositoryFactory")
public class UserRepositoryFactory {

	@Autowired
	private UserRepository userRepository;

	public UserRepository getRepositoryOfType(UserRepositoryType type) {
		if (UserRepositoryType.MONGO.equals(type)) {
			return userRepository;
		}
		return null;
	}
}
