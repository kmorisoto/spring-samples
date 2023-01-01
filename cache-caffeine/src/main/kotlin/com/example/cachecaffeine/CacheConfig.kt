package com.example.cachecaffeine

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfig {

    @Bean
    @Qualifier("isbn")
    fun caffeine(): Caffeine<Any, Any> = Caffeine.newBuilder().expireAfterAccess(2, TimeUnit.SECONDS)

    @Bean(name = ["cacheManager"])
    fun cacheManager(@Qualifier("isbn") caffeine: Caffeine<Any, Any>): CacheManager = CaffeineCacheManager("isbn").apply {
        setCaffeine(caffeine)
    }

    @Bean
    @Qualifier("title")
    fun caffeineTitle(): Caffeine<Any, Any> = Caffeine.newBuilder().expireAfterAccess(2, TimeUnit.SECONDS)

    @Bean(name = ["cacheManagerTitle"])
    fun cacheManagerTitle(@Qualifier("title") caffeineTitle: Caffeine<Any, Any>): CacheManager = CaffeineCacheManager("title").apply {
        setCaffeine(caffeineTitle)
    }
}
