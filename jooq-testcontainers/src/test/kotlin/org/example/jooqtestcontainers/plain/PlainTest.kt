package org.example.jooqtestcontainers.plain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlainTest : DBTest() {

    @Test
    fun insert() {
        dsl.execute("INSERT INTO users (username, email) VALUES ('alice', 'alice@example.com')")

        dsl.select().from("users").fetch().also {
            assertEquals("alice", it[0].get("username"))
            assertEquals("alice@example.com", it[0].get("email"))
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["alice", "bob", "charlie"])
    fun insert2(name: String) {
        dsl.execute("INSERT INTO users (username, email) VALUES ('$name', '$name@example.com')")

        dsl.select().from("users").fetch().also {
            assertEquals(name, it[0].get("username"))
            assertEquals("$name@example.com", it[0].get("email"))
        }
    }

    @Test
    fun select() {
        dsl.select().from("users").fetch().also {
            assertEquals(0, it.size)
        }
    }


}
