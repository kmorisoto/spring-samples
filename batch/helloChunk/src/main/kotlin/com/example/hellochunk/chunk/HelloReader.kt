package com.example.hellochunk.chunk

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.stereotype.Component

@Component
@StepScope
class HelloReader : ItemReader<String> {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    private val input: Array<String?> = arrayOf("Hello", "World", "foo", "bar", null)
    private var index = 0

    // Step実行前の処理
    @BeforeStep
    fun beforeStep(stepExecution: StepExecution) {
        val jobContext = stepExecution.jobExecution.executionContext
        jobContext.put("jobKey", "jobValue")

        val stepContext = stepExecution.executionContext
        stepContext.put("stepKey", "stepValue")
    }

    override fun read(): String? {
        val message = input[index++]
        log.info("Read: $message")
        return message
    }
}
