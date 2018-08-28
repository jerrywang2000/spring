package com.tools.auth.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tools.auth.domain.User;

@Service
public class RedisUserDetailsService implements UserDetailsService {

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = redisTemplate.opsForValue().get(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}		

		return user;
	}
}
