package org.example.jooqtestcontainers.container

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.testcontainers.containers.PostgreSQLContainer

abstract class DBTest {
    private val postgresContainer: PostgreSQLContainer<Nothing> = PostgreSQLContainer("postgres:15-alpine")
    val dsl: DSLContext

    init {
        postgresContainer.withInitScript("init.sql")
        postgresContainer.start()
        postgresContainer.createConnection("test")
        dsl = DSL.using(postgresContainer.jdbcUrl)
    }
}


