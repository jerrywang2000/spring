package com.tools.auth.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import com.tools.auth.domain.User;

@Configuration
public class RedisConfig {

	@Autowired
	JedisConnectionFactory jedisConnectionFactory;
	/*
	
    JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		 
		jedisConnFactory.setUsePool(true);
		jedisConnFactory.setHostName("slc15din.us.oracle.com");
		jedisConnFactory.setPort(6379);
		jedisConnFactory.setTimeout(0);
		jedisConnFactory.setPassword("");

		return jedisConnFactory;
    }
*/
    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<String, User>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }


}