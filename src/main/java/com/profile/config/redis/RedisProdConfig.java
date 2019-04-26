package com.profile.config.redis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 * 
 * 正式环境Redis配置<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月26日<br>
 */
@Configuration
@Profile("prod")
@ImportResource(locations = { "classpath*:app-redis-prod.xml" })
public class RedisProdConfig {

	public RedisProdConfig() {
		System.out.println("env: prod...");
	}

}
