package org.pti.rest;

import org.pti.poster.UserService;
import org.pti.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersRestService {

    private final UserService userService;

    @Autowired
    public UsersRestService(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping(value = "")
    public UserView getUsers() {
        return new UserView(userService.getAllUsers().get(0));
    }
}
