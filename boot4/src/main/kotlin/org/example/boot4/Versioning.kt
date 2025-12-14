package org.example.boot4

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Versioning {

    @GetMapping(path = ["/foo"], version = "1.0")
    fun foo(): String {
        return "boot4"
    }

    @GetMapping(path = ["/foo"], version = "2.0")
    fun foo2(): String {
        return "boot4 v2"
    }
}
