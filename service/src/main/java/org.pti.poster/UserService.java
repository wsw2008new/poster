package org.pti.poster;


import org.pti.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    
    public List<User> getAllUsers(){
        User.UserBuilder builder = User.builder();
        return Arrays.asList(builder.userName("vova").build());
    }
}
