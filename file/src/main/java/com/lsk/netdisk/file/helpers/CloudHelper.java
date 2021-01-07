package com.lsk.netdisk.file.helpers;

import com.lsk.netdisk.file.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class CloudHelper {
	private final LoadBalancerClient loadBalancerClient;
	private final RestTemplate restTemplate;

	public Integer getCurrentUserID(String token){
		ServiceInstance serviceInstance = loadBalancerClient.choose("auth");
		String path = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/uid?token={token}";
		log.info(token);
		Map<String,String> params = new HashMap<>();
		params.put("token",token);
		String response = restTemplate.getForObject(path,String.class,token);
		if(response == null || response.equals("") || response.equals("User didn't login.")){
			throw new RuntimeException("User didn't login");
		}
		return Integer.parseInt(response);
	}
}
