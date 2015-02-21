package org.pti.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pti.model.User;

public class UserView {
    @JsonIgnore
    private final User user;

    public UserView(User user) {
        this.user = user;
    }

    public String getName() {
        return user.getUserName();
    }
}
