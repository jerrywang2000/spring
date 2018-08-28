package com.tools.setup.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name = "object-service", fallback = ObjectServiceHystric.class)
@FeignClient("object-service")
public interface ObjectServiceClient {

	@RequestMapping(value="/object/deployment", method=RequestMethod.POST)
	public String deployRecord(@RequestBody Object input);
}
