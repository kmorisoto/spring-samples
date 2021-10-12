package com.example.buildimage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BuildimageApplication

fun main(args: Array<String>) {
    runApplication<BuildimageApplication>(*args)
}

@RestController
class Ping {
    @GetMapping("ping")
    fun ping(): String = "pong"
}
