package com.example.hellotasklet.tasklet

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("HelloTasklet2")
@StepScope
class HelloTasklet2(
    @Value("#{JobExecutionContext['jobKey']}")
    private val jobValue: String?,
    @Value("#{StepExecutionContext['stepKey']}")
    private val stepValue: String?
): Tasklet {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        log.info("Hello World2")
        log.info("jobKey=$jobValue")
        log.info("stepKey=$stepValue")
        return RepeatStatus.FINISHED
    }
}
