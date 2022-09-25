package com.example.rediscluster

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class RedisClusterApplicationTests {

    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

    @Test
    fun contextLoads() {
    }

    @Test
    fun a() {
        redisTemplate.opsForValue().set("foo", "bar")
        assertThat(redisTemplate.opsForValue().get("foo")).isEqualTo("bar")
    }

}
