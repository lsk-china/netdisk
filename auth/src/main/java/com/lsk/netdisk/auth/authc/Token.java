package com.lsk.netdisk.auth.authc;

import com.lsk.netdisk.auth.util.SpringUtil;
import com.lsk.netdisk.common.redis.core.RedisComponent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class Token {
	 private static RedisComponent redis = SpringUtil.getBean("redisComponent",RedisComponent.class);

	public static String newToken(){
		String token = UUID.randomUUID().toString();
		UserStatePair state = new UserStatePair(token,State.Guest);
		redis.set(token+"-STATE",state,0);
		return token;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserStatePair{
		private String token;
		private State state;
	}

}
