package com.example.testcontainers

import org.jooq.DSLContext
import org.jooq.impl.DSL.field
import org.jooq.impl.DSL.table
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val dslContext: DSLContext
) {
    fun findAll(): List<User> = dslContext
        .selectFrom("users")
        .fetchInto(User::class.java)

    fun add(name: String) {
        dslContext.insertInto(table("users"), field("name")).values(name).execute()
    }
}
