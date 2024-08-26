package org.example.jooqtestcontainers.container

import org.example.jooqtestcontainers.plain.DBTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SecondTest : DBTest() {

    @Test
    fun test() {
        dsl.execute("INSERT INTO users (username, email) VALUES ('alice', 'alice@example.com')")

        dsl.select().from("users").fetch().also {
            assertEquals("alice", it[0].get("username"))
            assertEquals("alice@example.com", it[0].get("email"))
        }
    }

    @Test
    fun test2() {
        println("Hello")
    }
}
