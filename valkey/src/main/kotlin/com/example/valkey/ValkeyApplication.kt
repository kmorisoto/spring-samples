package com.example.valkey

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ValkeyApplication

fun main(args: Array<String>) {
    runApplication<ValkeyApplication>(*args)
}
