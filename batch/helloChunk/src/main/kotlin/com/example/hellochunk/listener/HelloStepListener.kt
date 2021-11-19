package com.example.hellochunk.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.StepExecutionListener
import org.springframework.stereotype.Component

@Component
class HelloStepListener: StepExecutionListener {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun beforeStep(stepExecution: StepExecution) {
        log.info("Before Step: $stepExecution")
    }

    override fun afterStep(stepExecution: StepExecution): ExitStatus? {
        log.info("After Step: $stepExecution")
        return stepExecution.exitStatus
    }
}
