package org.pti.poster.repository.user;


import org.pti.poster.model.user.GenericUser;

import java.util.List;

public interface UserRepository {

	public GenericUser getUserById(String id);

	public GenericUser saveUser(GenericUser user);

	List<GenericUser> getAllUsers();

}
