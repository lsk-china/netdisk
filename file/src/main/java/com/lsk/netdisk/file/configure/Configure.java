package com.lsk.netdisk.file.configure;

import com.lsk.netdisk.file.helpers.CloudHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configure {
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	@Bean
	public CloudHelper cloudHelper(){
		return new CloudHelper(loadBalancerClient,restTemplate());
	}
}
