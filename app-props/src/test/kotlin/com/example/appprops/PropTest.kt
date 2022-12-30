package com.example.appprops

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
class PropTest {
    @Value("\${foo}")
    lateinit var foo: String
    @Value("\${bar}")
    lateinit var bar: String
    @Value("\${baz}")
    lateinit var baz: String

    @Test
    fun test() {
        assertThat(foo).isEqualTo("123")
        assertThat(bar).isEqualTo("456")
        assertThat(baz).isEqualTo("123")
    }
}

@SpringBootTest
@ActiveProfiles("test")
class TestProfilePropTest {
    @Value("\${foo}")
    lateinit var foo: String
    @Value("\${bar}")
    lateinit var bar: String
    @Value("\${baz}")
    lateinit var baz: String

    @Test
    fun test() {
        assertThat(foo).isEqualTo("1123")
        assertThat(bar).isEqualTo("4456")
        assertThat(baz).isEqualTo("1123")
    }
}
