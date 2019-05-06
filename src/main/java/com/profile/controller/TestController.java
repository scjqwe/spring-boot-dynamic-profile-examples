package com.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 测试<br>
 * 版权: Copyright (c) 2011-2019<br>
 * 
 * @author: 孙常军<br>
 * @date: 2019年4月26日<br>
 */
@RestController
@RequestMapping("/redis")
public class TestController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@RequestMapping("/set")
	public String set(String key, String value) {
		try {
			stringRedisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	@RequestMapping("/get")
	public String get(String key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		}
		return stringRedisTemplate.opsForValue().get(key);
	}

}
