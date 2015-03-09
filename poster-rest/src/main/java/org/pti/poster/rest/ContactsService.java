package org.pti.poster.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactsService {

    @RequestMapping("/")
    public  String test(){
        return "Up";

    }

}
