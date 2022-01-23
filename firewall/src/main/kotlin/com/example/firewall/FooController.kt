package com.example.firewall

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/foo")
class FooController {

    @GetMapping
    fun get(): String = "get"

    @PostMapping
    fun post(): String = "post"
}
