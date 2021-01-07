package com.lask.netdisk.files.tests;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Test {
	@org.junit.Test
	public void test1(){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("multipart/form-data");
		headers.setContentType(type);
		FileSystemResource fileSystemResource = new FileSystemResource("D://test1.c");
		MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
		form.add("file", fileSystemResource);
		form.add("filename","1.py");
		HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
		String s = restTemplate.postForObject("http://localhost:10003/uploadFile", files, String.class);
		System.out.println(s);

	}
}
