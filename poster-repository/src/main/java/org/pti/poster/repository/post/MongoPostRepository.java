package org.pti.poster.repository.post;


import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.user.GenericUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoPostRepository extends MongoRepository<GenericPost,String>{

	GenericPost getPostById(String id);

}
