package org.example.customrunimage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class CustomRunImageApplication

fun main(args: Array<String>) {
    runApplication<CustomRunImageApplication>(*args)
}
