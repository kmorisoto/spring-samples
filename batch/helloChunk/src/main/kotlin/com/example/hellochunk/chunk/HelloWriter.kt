package com.example.hellochunk.chunk

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
@StepScope
class HelloWriter: ItemWriter<String> {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun write(items: MutableList<out String>) {
        log.info("Writer: $items")
        log.info("==============")
    }
}
