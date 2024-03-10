package org.example.jooqcustomize

import org.jooq.DSLContext
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class AppRun(
    private val dsl: DSLContext
): ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        println(dsl.configuration().clock())
    }
}
