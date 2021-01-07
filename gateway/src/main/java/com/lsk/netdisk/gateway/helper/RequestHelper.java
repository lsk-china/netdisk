package com.lsk.netdisk.gateway.helper;

import com.lsk.netdisk.gateway.mapper.CookieMapper;
import com.lsk.netdisk.gateway.mapper.ParamMapper;
import com.lsk.netdisk.gateway.mapper.URLsMapper;
import com.lsk.netdisk.gateway.models.Cookie;
import com.lsk.netdisk.gateway.models.Param;
import com.lsk.netdisk.gateway.models.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RequestHelper {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private URLsMapper urlMapper;
	@Autowired
	private ParamMapper paramMapper;
	@Autowired
	private CookieMapper cookieMapper;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	public Object handleAuth(HttpServletRequest req){
		String path = req.getPathInfo();
		URL url = urlMapper.queryURLByPath(path);
		Integer urlID = url.getId();
		List<Param> params = paramMapper.queryParamsByUrlID(urlID);
		Map<String,String> paramMap = new HashMap<>();
		for(Param param : params){
			String name = param.getName();
			String value = req.getParameter(name);
			paramMap.put(name,value);
		}
		Map<String,String> cookieMap = new HashMap<>();
		List<Cookie> cookies = cookieMapper.queryCookiesByURLID(urlID);
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = getCookieValue(req,name);
			cookieMap.put(name,value);
		}
		ServiceInstance serviceInstance = loadBalancerClient.choose("auth");
		String address = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+path;
		log.info(address);
		HttpMethod method = HttpMethod.valueOf(req.getMethod());
		return sendRequest(method,address,paramMap,cookieMap);
	}
	private Object sendRequest(HttpMethod method,String url, Map<String,String> params, Map<String,String> cookies){
		List<String> cookieList = new ArrayList<>();
		for(Map.Entry<String,String> entry : cookies.entrySet()){
			String name = entry.getKey();
			String value = entry.getValue();
			cookieList.add(name+"="+value);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		if(cookieList.size() > 0){
			httpHeaders.put("Cookie",cookieList);
		}
		MultiValueMap<String,String> requestParams = new LinkedMultiValueMap<>();
		for(Map.Entry<String,String> entry:params.entrySet()){
			String name = entry.getKey();
			String value = entry.getValue();
			requestParams.add(name,value);
		}
		HttpEntity<MultiValueMap<String,String>> httpEntity = new HttpEntity<>(requestParams,httpHeaders);
		return restTemplate.exchange(url,method,httpEntity,Object.class);
	}
	private String getCookieValue(HttpServletRequest req, String name){
		javax.servlet.http.Cookie[] cookies = req.getCookies();
		for (javax.servlet.http.Cookie cookie : cookies){
			if (cookie.getName().equals(name)){
				return cookie.getValue();
			}
		}
		throw new RuntimeException("Cookie not found!");
	}
}
