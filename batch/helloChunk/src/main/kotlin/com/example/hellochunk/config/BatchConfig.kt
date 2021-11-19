package com.example.hellochunk.config

import com.example.hellochunk.listener.HelloJobListener
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepExecutionListener
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
@EnableBatchProcessing
class BatchConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,
    private val reader: ItemReader<String>,
    private val processor: ItemProcessor<String, String>,
    private val writer: ItemWriter<String>,
    private val jobListener: JobExecutionListener,
    private val stepListener: StepExecutionListener
) {
    @Bean
    fun chunkStep(): Step = stepBuilderFactory.get("HelloChunkStep")
        .chunk<String, String>(1)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .listener(stepListener)
        .build()

    @Bean
    fun chunkJob(): Job = jobBuilderFactory.get("HelloChunckJob")
        .incrementer(RunIdIncrementer())
        .start(chunkStep())
        .listener(jobListener)
        .build()
}
