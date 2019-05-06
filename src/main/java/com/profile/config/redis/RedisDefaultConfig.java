package com.profile.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * 
 * 本地环境Redis配置<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月26日<br>
 */
@Configuration
@Profile("default")
@ImportResource(locations = { "classpath*:app-redis-default.xml" })
public class RedisDefaultConfig {

	public RedisDefaultConfig() {
		System.out.println("env: default...");
	}

}
