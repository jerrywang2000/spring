package com.tools.setup.client;

import org.springframework.stereotype.Component;

import com.tools.setup.entity.User;

@Component
public class AuthServiceHystric implements AuthServiceClient {

	@Override
	public String createUser(User user) {
		// TODO Auto-generated method stub
		return "Call Auth Service failed";
	}
}

