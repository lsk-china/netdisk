package com.lsk.netdisk.common.redis;

import com.lsk.netdisk.common.redis.core.RedisComponent;
import com.lsk.netdisk.common.redis.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RedisProperties.class)
public class RedisAutoConfigure {
	private final RedisProperties properties;

	@Bean
	@ConditionalOnMissingBean(JedisPool.class)
	public JedisPool jedisPool(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(properties.getMaxIdle());
		jedisPoolConfig.setMaxTotal(properties.getMaxTotal());
		jedisPoolConfig.setMaxWaitMillis(properties.getMaxWaitMillis());
		String password = properties.getPassword();
		if (password == null || password.length() == 0) {
			return new JedisPool(jedisPoolConfig, properties.getHost(),
					properties.getPort(), properties.getTimeout());
		}
		return new JedisPool(jedisPoolConfig, properties.getHost(),
				properties.getPort(), properties.getTimeout(), properties.getPassword());
	}

	@Bean(name = "redisComponent")
	public RedisComponent redisComponent(JedisPool jedisPool){
		log.info("Creating bean: RedisComponent.");
		return new RedisComponent(jedisPool);
	}
}
