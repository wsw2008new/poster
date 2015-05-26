package org.pti.poster.repository.user;


import org.pti.poster.model.user.GenericUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<GenericUser,String>{

	GenericUser getUserByUserId(String userId);

}
