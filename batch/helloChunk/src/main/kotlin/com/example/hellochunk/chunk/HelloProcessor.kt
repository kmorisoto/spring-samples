package com.example.hellochunk.chunk

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.annotation.BeforeStep
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
@StepScope
class HelloProcessor(
    @Value("#{JobExecutionContext['jobKey']}")
    private val jobValue: String?,
    @Value("#{StepExecutionContext['stepKey']}")
    private val stepValue: String?
): ItemProcessor<String, String> {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @BeforeStep
    fun beforeStep(stepExecution: StepExecution) {
        log.info("jobKey=$jobValue")
        log.info("stepKey=$stepValue")
    }

    override fun process(item: String): String? {
        val proceed = "$item!!!"
        log.info("Processor: $proceed")
        return proceed
    }
}
