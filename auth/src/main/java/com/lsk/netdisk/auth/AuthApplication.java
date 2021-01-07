package com.lsk.netdisk.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//@ServletComponentScan("com.lsk.netdisk.auth.filters")
@SpringBootApplication
@ComponentScan
public class AuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class,args);
	}


}
