package com.example.multicachemanager

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

interface BookRepository {
    fun getByIsbn(isbn: String): Book
    fun getByTitle(title: String): Book
    fun getByIsbnAndTitle(isbn: String, title: String): Book
}

@Component
class SimpleBookRepository : BookRepository {

    @Cacheable(cacheNames = ["isbn"], cacheManager = "isbnCacheManager")
    override fun getByIsbn(isbn: String): Book {
        simulateSlowService()
        return Book(isbn, "Some book")
    }

    @Cacheable(cacheNames = ["title"], cacheManager = "titleCacheManager")
    override fun getByTitle(title: String): Book {
        simulateSlowService()
        return Book("isbn-1234", title)
    }

    @Cacheable(cacheNames = ["books"])
    override fun getByIsbnAndTitle(isbn: String, title: String): Book {
        simulateSlowService()
        return Book(isbn, title)
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
