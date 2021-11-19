package com.example.hellotasklet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloTaskletApplication

fun main(args: Array<String>) {
    runApplication<HelloTaskletApplication>(*args)
}
