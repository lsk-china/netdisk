package com.lsk.netdisk.common.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("common.redis")
public class RedisProperties {
	private String host = "127.0.0.1";

	private int port = 6379;

	private int timeout = 2000;

	private int maxIdle = 5;

	private int maxTotal = 10;

	private long maxWaitMillis = 10000;

	private String password;

}
