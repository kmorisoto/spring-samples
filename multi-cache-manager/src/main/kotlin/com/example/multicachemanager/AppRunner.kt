package com.example.multicachemanager

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class AppRunner(private val bookRepository: BookRepository) : CommandLineRunner {
    override fun run(vararg args: String) {
        logger.info(".... Fetching books by isbn")
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"))
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"))
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))
        logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"))

        logger.info(".... Fetching books by title")
        logger.info("title-1234 -->" + bookRepository.getByTitle("title-1234"))
        logger.info("title-4567 -->" + bookRepository.getByTitle("title-4567"))
        logger.info("title-1234 -->" + bookRepository.getByTitle("title-1234"))
        logger.info("title-4567 -->" + bookRepository.getByTitle("title-4567"))
        logger.info("title-1234 -->" + bookRepository.getByTitle("title-1234"))
        logger.info("title-1234 -->" + bookRepository.getByTitle("title-1234"))

        logger.info(".... Fetching books")
        logger.info("isbn-1234, title-1234 -->" + bookRepository.getByIsbnAndTitle("isbn-1234", "title-1234"))
        logger.info("isbn-4567, title-4567 -->" + bookRepository.getByIsbnAndTitle("isbn-4567", "title-4567"))
        logger.info("isbn-1234, title-1234 -->" + bookRepository.getByIsbnAndTitle("isbn-1234", "title-1234"))
        logger.info("isbn-4567, title-4567 -->" + bookRepository.getByIsbnAndTitle("isbn-4567", "title-4567"))
        logger.info("isbn-1234, title-1234 -->" + bookRepository.getByIsbnAndTitle("isbn-1234", "title-1234"))
        logger.info("isbn-1234, title-1234 -->" + bookRepository.getByIsbnAndTitle("isbn-1234", "title-1234"))
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(AppRunner::class.java)
    }
}
