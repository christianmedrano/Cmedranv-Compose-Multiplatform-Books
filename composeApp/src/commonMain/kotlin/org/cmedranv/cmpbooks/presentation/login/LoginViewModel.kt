package org.cmedranv.cmpbooks.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.cmedranv.cmpbooks.domain.usecase.auth.LoginUseCase

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var username by mutableStateOf("user")
        private set

    var password by mutableStateOf("password")
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onLoginClick() {
        isLoading = true
        error = null
        loginSuccess = false
        viewModelScope.launch {
            val result = loginUseCase(username, password)
            result.onSuccess { user ->
                loginSuccess = user.isLoggedIn
                if (!user.isLoggedIn) {
                    error = "Credenciales inválidas"
                }
            }.onFailure { e ->
                error = e.message ?: "Error desconocido al intentar login."
            }
            isLoading = false
        }
    }
}