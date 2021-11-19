package com.example.hellochunk.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.stereotype.Component

@Component
class HelloJobListener: JobExecutionListener {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun beforeJob(jobExecution: JobExecution) {
        log.info("Before Job: $jobExecution")
    }

    override fun afterJob(jobExecution: JobExecution) {
        log.info("After Job: $jobExecution")
    }
}
