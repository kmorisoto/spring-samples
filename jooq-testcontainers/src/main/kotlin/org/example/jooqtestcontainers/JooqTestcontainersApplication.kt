package org.example.jooqtestcontainers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqTestcontainersApplication

fun main(args: Array<String>) {
    runApplication<JooqTestcontainersApplication>(*args)
}
