package com.lsk.netdisk.auth.helpers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class CloudHelper {
	private final LoadBalancerClient loadBalancerClient;
	private final RestTemplate restTemplate;

	private String getURL(String api){
		ServiceInstance serviceInstance = loadBalancerClient.choose("file");
		String host = serviceInstance.getHost();
		String port = Integer.toString(serviceInstance.getPort());
		return "http://"+host+":"+port+"/"+api;
	}

	public void createUserDir(String username,String token){
		log.info(token);
		Map<String,Object> params = new HashMap<>();
		params.put("username",username);
		params.put("token",token);
		String url = getURL("/createUserDir?token={token}&username={username}");
		String response = restTemplate.getForObject(url,String.class,token,username);
	}
}
