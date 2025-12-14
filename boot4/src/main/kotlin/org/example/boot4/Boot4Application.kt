package org.example.boot4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Boot4Application

fun main(args: Array<String>) {
    runApplication<Boot4Application>(*args)
}
