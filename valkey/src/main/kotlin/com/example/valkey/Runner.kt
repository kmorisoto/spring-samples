package com.example.valkey

import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component


@Component
class Runner(
    private val template: RedisTemplate<String, String>
) : ApplicationRunner {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun run(args: ApplicationArguments?) {


        template.opsForValue().set("foo", "bar")

        log.info("Value at foo:" + template.opsForValue().get("foo"))

    }
}
