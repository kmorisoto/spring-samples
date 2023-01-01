package com.example.cachecaffeine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CacheCaffeineApplication

fun main(args: Array<String>) {
    runApplication<CacheCaffeineApplication>(*args)
}
