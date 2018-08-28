package com.tools.auth.service;

import com.tools.auth.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Override
	public void create(User user) {

//				stringRedisTemplate.opsForValue().set("aaa", "111");
//				Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
			
		User existing = redisTemplate.opsForValue().get(user.getUsername());
		Assert.isNull(existing, "user already exists: " + user.getUsername());

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		redisTemplate.opsForValue().set(user.getUsername(), user);

		log.info("new user has been created: {}", user.getUsername());
	}
	
	public User findUser(String username) {
		User user = null;
		
		try {
			user = redisTemplate.opsForValue().get(username);			
		} catch (Exception e) {
			System.out.println("The user is not existing.");
		}
		return user;
	}
}
