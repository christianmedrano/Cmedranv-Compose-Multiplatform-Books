package org.cmedranv.cmpbooks.domain.usecase.auth

import org.cmedranv.cmpbooks.domain.model.User
import org.cmedranv.cmpbooks.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        return authRepository.login(username, password)
    }
}