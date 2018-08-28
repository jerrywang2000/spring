package com.tools.auth.controller;

import com.tools.auth.domain.User;
import com.tools.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public User findUser(@PathVariable String username) {
		return userService.findUser(username);
	}
	
	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(method = RequestMethod.POST)
	public String createUser(@Valid @RequestBody User user) {
		String returnValue = "";
		User existingUser = userService.findUser(user.getUsername());
		if (existingUser == null) {
			userService.create(user);
			returnValue = user.getUsername();
		} else {
			returnValue = "User is existed.";
		}
		return returnValue;
	}
}
