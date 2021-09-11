package com.example.testcontainers

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StartUpListener(
    private val userService: UserService
) {

    @EventListener(ApplicationReadyEvent::class)
    fun findAll() {
        userService.findAll()
            .also { println(it) }
    }

    @EventListener(ApplicationReadyEvent::class)
    fun add() {
        userService.add()
    }
}
