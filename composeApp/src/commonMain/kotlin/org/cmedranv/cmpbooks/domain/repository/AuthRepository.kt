package org.cmedranv.cmpbooks.domain.repository

import org.cmedranv.cmpbooks.domain.model.User

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
}