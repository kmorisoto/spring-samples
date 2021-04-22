package com.example.owasptest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/foo")
class FooController {
    @GetMapping
    fun get():ResponseEntity<String> = ResponseEntity.ok(LocalDateTime.now().toString())
}
