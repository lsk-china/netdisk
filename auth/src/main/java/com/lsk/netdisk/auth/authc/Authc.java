package com.lsk.netdisk.auth.authc;

import com.lsk.netdisk.auth.mappers.UserMapper;
import com.lsk.netdisk.auth.model.User;
import com.lsk.netdisk.auth.util.SpringUtil;
import com.lsk.netdisk.auth.util.StringUtil;
import com.lsk.netdisk.common.redis.core.RedisComponent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
public class Authc {
	private static UserMapper mapper = SpringUtil.getBean(UserMapper.class);

	private static RedisComponent redis = SpringUtil.getBean("redisComponent",RedisComponent.class);

	public static LoginResult login(String token,String password,String username){
		if (StringUtil.isEmpty(token,password,username)){
			return new LoginResult(false,"Token or password or username is empty.");
		}
		User user = mapper.queryUserByName(username);
		if (user == null){
			return new LoginResult(false,"User not found.");
		}
		if (!(user.getState() == 1)){
			return new LoginResult(false,"User is disabled");
		}
		if (!(user.getPassword().equals(password))){
			return new LoginResult(false,"Password is wrong.");
		}
		Token.UserStatePair state = new Token.UserStatePair(token,State.Login);
		redis.set(token+"-STATE",state,0);
		redis.set(token+"-UID",user.getId().toString(),0);
		return new LoginResult(true,"");
	}

	public static String register(String username,String password,String email,String token){
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setState(0);
		user.setRole("user");
		mapper.addUser(user);
		String registerCode = UUID.randomUUID().toString();
		System.out.println(registerCode);
		redis.set(token+"-REG-CODE",registerCode,0);
		Integer id = mapper.queryUserByName(username).getId();
		redis.set(token+"-UID",id,0);
		return registerCode;
	}
	public static void doneRegister(String token,String regCode) {
		String localRegCode = redis.get(token + "-REG-CODE", String.class);
		if (StringUtil.isEmpty(localRegCode, regCode)) {
			throw new RuntimeException("regcode is empty");
		}
		if (!localRegCode.equals(regCode)) {
			throw new RuntimeException(("regcode is wrong."));
		}
		Integer id = Integer.parseInt(redis.rawGet(token + "-UID"));
		mapper.enableUser(id);
		redis.set(token+"-STATE",new Token.UserStatePair(token,State.Login),0);
		redis.delete(token+"-REG-CODE");
	}
	public static void logout(String token){
		redis.set(token+"-STATE",new Token.UserStatePair(token,State.Guest),0);
		redis.delete(token+"-UID");
	}
	public static User getCurrentUser(String token){
		Integer uid = redis.get(token+"-UID",Integer.class);
		User user = mapper.queryUserById(uid);
		return user;
	}
	public static boolean isUserLogined(String token){
		State state = redis.get(token+"-STATE", Token.UserStatePair.class).getState();
		if(state.equals(State.Login)){
			return true;
		}else{
			return false;
		}
	}
	private static State stringToState(String state){
		log.info(state);
		switch (state){
			case "Login":
				return State.Login;
			default:
				return State.Guest;
		}
	}
	public static String getUID(String token){
		Integer uid = redis.get(token+"-UID",Integer.class);
		return uid.toString();
	}
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LoginResult{
		private boolean isSuccess;
		private String message;
	}
}
