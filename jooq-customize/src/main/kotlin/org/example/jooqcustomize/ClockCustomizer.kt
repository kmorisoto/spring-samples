package org.example.jooqcustomize

import org.jooq.impl.DefaultConfiguration
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.Instant

@Component
class ClockCustomizer: DefaultConfigurationCustomizer {
    override fun customize(configuration: DefaultConfiguration) {
        val clock = Clock.fixed(Instant.parse("2024-03-01T00:00:00Z"), Clock.systemDefaultZone().zone)
        configuration.set(clock)
    }
}
