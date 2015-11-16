package org.pti.poster.repository.post;


import org.bson.types.ObjectId;
import org.pti.poster.model.post.GenericPost;
import org.pti.poster.model.user.GenericUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoPostRepository extends MongoRepository<GenericPost,String>{

	GenericPost getPostById(String id) throws Exception;

	List<GenericPost> getPostsByUserObjectId(ObjectId userObjectId) throws Exception;

}
