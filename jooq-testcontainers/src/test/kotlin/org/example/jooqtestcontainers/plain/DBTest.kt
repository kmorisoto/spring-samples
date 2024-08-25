package org.example.jooqtestcontainers.plain

import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.sql.Connection
import java.sql.DriverManager

abstract class DBTest {

    private lateinit var connection: Connection
    lateinit var dsl: DSLContext

    @BeforeEach
    fun setUp() {
        connection =
            DriverManager.getConnection("jdbc:tc:postgresql:15-alpine:///test?TC_DAEMON=true?TC_INITSCRIPT=file:src/test/resources/init.sql")
                .also {
                    it.autoCommit = false
                }
        dsl = DSL.using(connection)
    }

    @AfterEach
    fun tearDown() {
        connection.rollback()
        connection.close()
    }
}
