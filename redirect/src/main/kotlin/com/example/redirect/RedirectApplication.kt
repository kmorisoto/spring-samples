package com.example.redirect

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class RedirectApplication

fun main(args: Array<String>) {
    runApplication<RedirectApplication>(*args)
}

@Controller
@RequestMapping("/foo")
class FooController {
    @GetMapping
    fun redirect(): String = "redirect:https://example.com"
}

@RestController
@RequestMapping("/bar")
class BarController {
    @GetMapping
    fun redirect(): ResponseEntity<String> {
        val headers = HttpHeaders().apply {
            add("Location", "https://example.com")
        }
        return ResponseEntity(headers, HttpStatus.FOUND)
    }
}
