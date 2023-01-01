package com.example.springnative

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringNativeApplication

fun main(args: Array<String>) {
    runApplication<SpringNativeApplication>(*args)
}

@RestController
class Hello {
    @GetMapping("/")
    fun hello():ResponseEntity<String> = ResponseEntity.ok("Hello world")
}
