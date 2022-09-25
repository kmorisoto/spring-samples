package com.example.rediscluster

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class RedisTest {

    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

    @ParameterizedTest
    @CsvSource(
        *[
            "apple,         1",
            "banana,        2",
            "lemon,         100",
            "lime,          2_000",
            "strawberry,    700_000"
        ]
    )
    fun test(fruit: String, rank: Int) {
        redisTemplate.opsForValue().set(fruit, rank.toString())
        assertThat(redisTemplate.opsForValue().get(fruit)).isEqualTo(rank.toString())
    }

}
