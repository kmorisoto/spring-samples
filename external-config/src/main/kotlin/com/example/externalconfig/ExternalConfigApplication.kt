package com.example.externalconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExternalConfigApplication

fun main(args: Array<String>) {
    runApplication<ExternalConfigApplication>(*args)
}
