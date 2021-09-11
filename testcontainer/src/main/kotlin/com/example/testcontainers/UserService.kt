package com.example.testcontainers

import io.github.serpro69.kfaker.faker
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun findAll(): List<User> = userRepository.findAll()

    @Transactional
    fun add() {
        val faker = faker { }
        userRepository.add(faker.name.name())
    }
}
