package com.example.bootbuildimageargs

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class InitRunner: ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        println(System.getenv("MESSAGE"))
    }
}
