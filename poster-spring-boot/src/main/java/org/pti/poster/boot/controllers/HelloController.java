package org.pti.poster.boot.controllers;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
