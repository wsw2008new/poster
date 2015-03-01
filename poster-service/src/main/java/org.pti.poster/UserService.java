package org.pti.poster;


import org.pti.poster.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    
    public List<User> getAllUsers(){
        return Arrays.asList(new User());
    }
}
