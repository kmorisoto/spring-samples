package com.example.hellochunk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloChunkApplication

fun main(args: Array<String>) {
    runApplication<HelloChunkApplication>(*args)
}
