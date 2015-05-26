package org.pti.poster.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.pti.poster.model.user.GenericUserType;

import java.util.List;

public class NewUserDto extends GenericUserDto {

	public NewUserDto() {
		type = GenericUserType.UNREGISTERED_USER;
	}

	@JsonIgnore
	@Override
	public String getUserId() {
		return userId;
	}

	@JsonIgnore
	@Override
	public List<String> getErrorMessages() {
		return super.getErrorMessages();
	}
}
