package com.example.multicachemanager

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfig: CachingConfigurerSupport() {

    // 必ず定義しないと起動できない
    @Bean
    override fun cacheManager(): CacheManager = CaffeineCacheManager().apply {
        setCaffeine(Caffeine.newBuilder().expireAfterWrite(23, TimeUnit.SECONDS))
    }

    @Bean
    fun isbnCacheManager(): CacheManager = CaffeineCacheManager("isbn").apply {
        setCaffeine(Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS))
    }

    @Bean
    fun titleCacheManager(): CacheManager = CaffeineCacheManager("title").apply {
        setCaffeine(Caffeine.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS))
    }
}
