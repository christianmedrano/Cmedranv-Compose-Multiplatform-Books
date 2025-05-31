package org.cmedranv.cmpbooks.data.repository

import kotlinx.coroutines.delay
import org.cmedranv.cmpbooks.domain.model.User
import org.cmedranv.cmpbooks.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(username: String, password: String): Result<User> {
        delay(1000) // Simula un delay de red
        return if (username == "user" && password == "password") {
            Result.success(User(username = username, isLoggedIn = true))
        } else {
            Result.failure(Exception("Credenciales inválidas"))
        }
    }
}