package com.tools.auth.service;

import com.tools.auth.domain.User;

public interface UserService {

	void create(User user);
	User findUser(String username);
}
