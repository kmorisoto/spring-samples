package com.example.rediscluster

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@SpringBootApplication
class RedisClusterApplication

fun main(args: Array<String>) {
    runApplication<RedisClusterApplication>(*args)
}

@Component
class RedisApplicationRunner(
    private val redisTemplate: StringRedisTemplate
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        redisTemplate.opsForValue().set("foo", "bar")
        println(redisTemplate.opsForValue().get("foo"))
        println(redisTemplate.opsForValue().get("hello"))
    }
}
