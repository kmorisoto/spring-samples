package com.example.firewall

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FirewallApplication

fun main(args: Array<String>) {
    runApplication<FirewallApplication>(*args)
}
