package com.example.cachecaffeine

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

interface BookRepository {
    fun getByIsbn(isbn: String): Book
    fun getByTitle(title: String): Book
}

@Component
class SimpleBookRepository : BookRepository {

    @Cacheable(cacheNames = ["isbn"], cacheManager = "cacheManager")
    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()
        return Book(isbn, "Some book")
    }

    @Cacheable(cacheNames = ["title"], cacheManager = "cacheManagerTitle")
    override fun getByTitle(title: String): Book {
        simulateSlowService()
        return Book("isbn-1234", title)
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
