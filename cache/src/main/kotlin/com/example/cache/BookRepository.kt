package com.example.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.CaffeineSpec
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

interface BookRepository {
    fun getByIsbn(isbn: String): Book
}

@Component
class SimpleBookRepository : BookRepository {

    @Cacheable("books")
    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()
        return Book(isbn, "Some book")
    }

    // Don't do this at home
    private fun simulateSlowService() {
        try {
            val time = 3000L
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            throw IllegalStateException(e)
        }
    }
}
