package com.example.externalconfig

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class ValueRunner(
    @Value("\${spring.application.name}")
    private val name: String
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        println(name)
    }
}
