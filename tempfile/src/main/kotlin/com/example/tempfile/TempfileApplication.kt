package com.example.tempfile

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component

@SpringBootApplication
class TempfileApplication

fun main(args: Array<String>) {
    runApplication<TempfileApplication>(*args)
}

@Component
class Foo: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        println(kotlin.io.path.createTempFile().toString())
        println(kotlin.io.path.createTempFile().toString())
        println(kotlin.io.path.createTempFile().toString())
    }
}
