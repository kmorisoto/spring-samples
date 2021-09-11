package com.example.springerrorpage

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TestController {

    @GetMapping
    fun a(): String = "index"

    @GetMapping("a")
    fun aa(): String {
        TODO()
    }

    @GetMapping("illegal")
    fun illegal(): String {
        throw IllegalArgumentException("aaa")
    }

    @GetMapping("interceptor")
    fun interceptor(): String = "index"
}
