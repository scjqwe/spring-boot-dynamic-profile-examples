package com.profile.config.redis;

import java.io.Serializable;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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

	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
		stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
		// stringRedisTemplate.setEnableTransactionSupport(true);
		return stringRedisTemplate;
	}

	@Bean
	public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<String, Serializable>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());// 设置值序列化
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		// redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	@Bean
	public LettuceConnectionFactory lettuceConnectionFactory(RedisStandaloneConfiguration standaloneConfig, LettuceClientConfiguration clientConfig) {
		return new LettuceConnectionFactory(standaloneConfig, clientConfig);
	}

	/**
	 * 配置LettuceClientConfiguration 包括线程池配置和安全项配置
	 * 
	 * @param genericObjectPoolConfig
	 *            common-pool2线程池
	 * @return lettuceClientConfiguration
	 */
	@Bean
	public LettuceClientConfiguration lettuceClientConfiguration(GenericObjectPoolConfig genericObjectPoolConfig) {
		return LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).build();
	}

	@Bean
	public GenericObjectPoolConfig genericObjectPoolConfig(CommonPool2Properties commonPool2Properties) {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(commonPool2Properties.getMaxIdle());
		poolConfig.setMinIdle(commonPool2Properties.getMinIdle());
		poolConfig.setMaxTotal(commonPool2Properties.getMaxTotal());
		// todo 其他配置
		return poolConfig;
	}

}
