package com.lsk.netdisk.auth.configures;

import com.lsk.netdisk.auth.helpers.CloudHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
	private RestTemplate restTemplate = new RestTemplate();
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Bean
	public RestTemplate restTemplate(){
		return restTemplate;
	}
	@Bean
	public CloudHelper cloudHelper(){
		return new CloudHelper(loadBalancerClient,restTemplate);
	}
}
