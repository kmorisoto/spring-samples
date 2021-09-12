package com.example.testcontainers

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import javax.sql.DataSource

//fun getDataSource(): DataSource = HikariDataSource(hikariConfig())
//
//fun hikariConfig(): HikariConfig {
//    postgreSQLContainer.start()
//    return HikariConfig().apply {
//        jdbcUrl = postgreSQLContainer.jdbcUrl
//        username = "foo"
//        password = "password"
//    }
//}
//
//@Container
//private val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:12.6-alpine").apply {
//    withDatabaseName("foo")
//    withUsername("postgres")
//    withPassword("password")
//    withInitScript("init_postgres.sql")
//}

@Testcontainers
internal class UserRepositoryTest3 {

    @Container
    private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))
        .apply {
            withDatabaseName("foo")
            withInitScript("init_postgres.sql")
        }

    @Test
    fun aa() {
        assertTrue(postgreSQLContainer.isRunning)

        val config = HikariConfig().apply {
            jdbcUrl = postgreSQLContainer.jdbcUrl
            username = "foo"
            password = "password"
        }

        println(HikariDataSource(config).username)
    }
}

//@Testcontainers
//internal class UserRepositoryTest2 {
//
//    @Test
//    fun test() {
//        println(getDataSource())
//        println(getDataSource().connection)
//        println("aaaaaaaaaaaa")
//    }
//}

@Testcontainers
internal class UserRepositoryTest {

    @Container
    private val postgreSQLContainer = PostgreSQLContainer<Nothing>(DockerImageName.parse("postgres:12.6-alpine"))
        .apply {
            withDatabaseName("foo")
            withInitScript("init_postgres.sql")
        }


    @Test
    fun test() {
        postgreSQLContainer.start()
        val config = HikariConfig().apply {
            jdbcUrl = postgreSQLContainer.jdbcUrl
            username = postgreSQLContainer.username
            password = postgreSQLContainer.password
        }
        val dataSource: DataSource =
            HikariDataSource(config)
//                .apply {
////            jdbcUrl = "jdbc:tc:postgresql:12.6:///postgres?user=postgres&password=password"
//                jdbcUrl = postgreSQLContainer.jdbcUrl
//                username = postgreSQLContainer.username
//                password = postgreSQLContainer.password
//            }
        println(postgreSQLContainer)
        println(postgreSQLContainer.host)
        println(postgreSQLContainer.jdbcUrl)
        println(postgreSQLContainer.password)
        assertTrue(postgreSQLContainer.isRunning)
        println(dataSource.connection)
    }


    @Test
    fun test2() {
        assertTrue(postgreSQLContainer.isRunning)
    }
}
