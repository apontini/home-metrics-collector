package me.apontini.hmc.user.application

import me.apontini.hmc.user.domain.User
import me.apontini.hmc.user.exceptions.UserAlreadyExistsException
import me.apontini.hmc.user.exceptions.UserNotFoundException
import me.apontini.hmc.user.infrastructure.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserService : KoinComponent {
    private val userRepository by inject<UserRepository>()

    fun createUser(email: String, name: String): User {
        userRepository.findByEmail(email)?.let { throw UserAlreadyExistsException() }

        return userRepository.save(
            User(email = email, name = name)
        )
    }

    fun getUserById(id: String): User? = userRepository.findById(id)

    fun updateUser(id: String, update: User.() -> User): User {
        val user = userRepository.findById(id) ?: throw UserNotFoundException()
        return userRepository.save(user.update())
    }

    fun deleteUser(id: String) {
        val user = userRepository.findById(id) ?: throw UserNotFoundException()
        userRepository.delete(user)
    }
}