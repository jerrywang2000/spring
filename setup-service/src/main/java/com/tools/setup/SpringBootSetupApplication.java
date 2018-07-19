package com.tools.setup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableEntityLinks;

@SpringBootApplication
@MapperScan({"com.tools.setup.dao"})
//@EnableResourceServer
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@Configuration
//@EnableEntityLinks
public class SpringBootSetupApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSetupApplication.class, args);
	}
}
