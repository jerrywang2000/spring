package com.tools.objectManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@MapperScan("com.tools.objectManagement.entity.mapper")
//@EnableResourceServer
@EnableDiscoveryClient
@EnableFeignClients
@EnableConfigurationProperties
@Configuration
public class SpringBootObjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootObjectManagementApplication.class, args);
	}
}
