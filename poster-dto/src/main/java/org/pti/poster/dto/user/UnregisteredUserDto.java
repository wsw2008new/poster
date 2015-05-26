package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.pti.poster.model.user.GenericUserType;

public class UnregisteredUserDto extends GenericUserDto {

	public UnregisteredUserDto() {
		type = GenericUserType.UNREGISTERED_USER;
	}

	@JsonIgnore
	@Override
	public String getUserId() {
		return userId;
	}
}
