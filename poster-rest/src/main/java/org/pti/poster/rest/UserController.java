package org.pti.poster.rest;

import org.pti.poster.dto.user.GenericUserCollectionDto;
import org.pti.poster.dto.user.GenericUserDto;
import org.pti.poster.dto.user.NewUserDto;
import org.pti.poster.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/poster/api/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "OK";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public
	GenericUserDto register(@RequestBody NewUserDto user) {
		return userService.createUser(user);
	}

	@RequestMapping(value = "/registered/all/", method = RequestMethod.GET)
	@ResponseBody
	public
	GenericUserCollectionDto getAllRegistered() {
		return userService.getAllUsers();
	}

}
