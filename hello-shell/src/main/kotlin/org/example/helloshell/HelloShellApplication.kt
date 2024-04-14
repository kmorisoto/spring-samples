package org.example.helloshell

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloShellApplication

fun main(args: Array<String>) {
    runApplication<HelloShellApplication>(*args)
}
