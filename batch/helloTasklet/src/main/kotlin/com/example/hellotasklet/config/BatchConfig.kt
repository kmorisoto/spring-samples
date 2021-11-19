package com.example.hellotasklet.config

import com.example.hellotasklet.tasklet.HelloTasklet
import com.example.hellotasklet.tasklet.HelloTasklet2
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@EnableBatchProcessing
class BatchConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    @Qualifier("HelloTasklet") private val helloTasklet: Tasklet,
    @Qualifier("HelloTasklet2") private val helloTasklet2: Tasklet,
) {
    @Bean
    fun taskletStep1(): Step = stepBuilderFactory.get("HelloTaskletStep1").tasklet(helloTasklet).build()

    @Bean
    fun taskletStep2(): Step = stepBuilderFactory.get("HelloTaskletStep2").tasklet(helloTasklet2).build()

    @Bean
    fun taskletJob(): Job =
        jobBuilderFactory.get("HelloTaskletJob")
            // JOB IDを設定する. 別コンテナで並列で実行されたらどうなる？
            .incrementer(RunIdIncrementer())
            .start(taskletStep1())
            .next(taskletStep2())
            .build()
}
