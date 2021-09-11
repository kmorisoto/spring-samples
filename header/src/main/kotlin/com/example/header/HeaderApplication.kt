package com.example.header

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class HeaderApplication

fun main(args: Array<String>) {
    runApplication<HeaderApplication>(*args)
}

@RestController
class FooController {

    @GetMapping
    fun a(): String = "a"

    @GetMapping
    @RequestMapping(headers = ["User-Agent=facebookexternalhit/1.1;line-poker/1.0,Twitterbot/1.0"])
    fun b(): String = "b"

}
