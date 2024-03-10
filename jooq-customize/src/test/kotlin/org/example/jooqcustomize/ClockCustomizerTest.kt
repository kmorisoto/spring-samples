package org.example.jooqcustomize

import org.jooq.DSLContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClockCustomizerTest(
) {
    @Autowired
    private lateinit var dsl: DSLContext

    @Test
    fun clock() {
        val clock = dsl.configuration().clock()
        assertEquals("2024-03-01T00:00:00Z", clock.instant().toString())
    }
}
