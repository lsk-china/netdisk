package com.lsk.netdisk.auth.controller;

import com.lsk.netdisk.auth.authc.Authc;
import com.lsk.netdisk.auth.authc.Token;
import com.lsk.netdisk.auth.helpers.CloudHelper;
import com.lsk.netdisk.auth.util.ArrayUtil;
import com.lsk.netdisk.auth.util.SpringUtil;
import com.lsk.netdisk.auth.util.StringUtil;
import com.lsk.netdisk.auth.util.WebUtil;
import com.lsk.netdisk.common.response.aspect.annotation.JsonReturn;
import com.lsk.netdisk.common.response.aspect.annotation.WithCors;
import com.lsk.netdisk.auth.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
public class Controller {
	@Autowired
	private RestTemplate template;
	@Autowired
	private JavaMailSenderImpl sender;
	@Autowired
	private UserMapper mapper;
	@Autowired
	private CloudHelper cloudHelper;

	@GetMapping("/token")
	@WithCors(req=1,resp=0)
	public void token(HttpServletResponse resp, HttpServletRequest req) {
		Cookie token = new Cookie("token", Token.newToken());
		if(ArrayUtil.contains(req.getCookies(),token)){
			return;
		}
		resp.addCookie(token);

	}

	@PostMapping("/login")
	@JsonReturn
	@WithCors(req=2,resp=3)
	public Object login(String username,String password,HttpServletRequest req,HttpServletResponse resp){
		String token = WebUtil.getCookie(req.getCookies(),"token").getValue();
		log.info(token);
		Authc.LoginResult result = Authc.login(token,password,username);
		if (result.isSuccess()){
			return "Success.";
		}else{
			throw new RuntimeException("Login failed.Caused by: "+result.getMessage());
		}
	}

	@JsonReturn
	@GetMapping("/hasPermission")
	public Object hasPermission(String url, ServletRequest req){
		return true;
	}

	@PostMapping("/register")
	@WithCors(req = 3,resp=4)
	public String register(String username,String password,String email,HttpServletRequest req,HttpServletResponse resp){
		Cookie tokenCookie = WebUtil.getCookie(req.getCookies(),"token");
		log.info("Register: "+username+" "+password+" "+email);
		String registerCode = Authc.register(username,password,email,tokenCookie.getValue());
		return registerCode;
	}

	@PostMapping("/doneRegister")
	@JsonReturn
	@WithCors(req =2,resp=3)
	public Object doneRegister(@RequestParam("regCode") String regCode, @CookieValue("token") String token, HttpServletRequest req,HttpServletResponse resp){
		log.info(regCode);
		Authc.doneRegister(token,regCode);
		cloudHelper.createUserDir(Authc.getCurrentUser(token).getName(),token);
		return "Success.";
	}
	@GetMapping("/isUserExists")
	@JsonReturn
	public Object isUserExists(String username){
		if(mapper.queryUserByName(username) == null){
			return false;
		}else{
			return true;
		}
	}
	@GetMapping("/currentUser")
	@JsonReturn
	public Object currentUser(@CookieValue("token") String token){
		if(!Authc.isUserLogined(token)){
			throw new RuntimeException("User didn't login.");
		}
		return Authc.getCurrentUser(token);
	}
	@GetMapping("/uid")
	public String uid(String token){
//		if (!Authc.isUserLogined(token)){
//			return "User didn't login.";
//		}
		log.info("token: "+token);
		return Authc.getUID(token);
	}

}
