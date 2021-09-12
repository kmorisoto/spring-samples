package com.example.testcontainers

import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import javax.sql.DataSource


@Testcontainers
internal class ExampleTest {

    @Nested
    inner class Simple {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))

        @Test
        fun test() {
            assertTrue(postgreSQLContainer.isRunning)
        }
    }

    @Nested
    inner class Config {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))
            .apply {
                withDatabaseName("foo")
                withUsername("bar")
                withPassword("baz")
            }

        @Test
        fun test() {
            assertThat(postgreSQLContainer.databaseName).isEqualTo("foo")
            assertThat(postgreSQLContainer.username).isEqualTo("bar")
            assertThat(postgreSQLContainer.password).isEqualTo("baz")
        }
    }

    @Nested
    inner class Env {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))
            .apply {
                withEnv("FOO_BAR", "fooBar")
            }

        @Test
        fun test() {
            assertThat(postgreSQLContainer.envMap["FOO_BAR"]).isEqualTo("fooBar")
        }
    }

    @Nested
    inner class InitScript {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))
            .apply {
                withInitScript("create_table.sql")
            }

        @Test
        fun test() {
            assertTrue(postgreSQLContainer.isRunning)
        }
    }

    @Nested
    inner class ExecuteFlyway {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))

        @BeforeEach
        fun setUp() {
            Flyway.configure()
                .dataSource(postgreSQLContainer.jdbcUrl, postgreSQLContainer.username, postgreSQLContainer.password)
                .load()
                .migrate()
        }

        @Test
        fun test() {
            assertTrue(postgreSQLContainer.isRunning)
        }
    }


    @Nested
    inner class ExecuteJooq {
        @Container
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))

        @Test
        fun test() {
            val dslContext =
                DSL.using(postgreSQLContainer.jdbcUrl, postgreSQLContainer.username, postgreSQLContainer.password)
            val value = dslContext.select(DSL.field("1")).fetchOne()?.getValue(0)
            assertThat(value).isNotNull.isEqualTo(1)
        }
    }
}
