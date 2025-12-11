package com.example.runapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runapp.network.LoginRequest
import com.example.runapp.network.RegisterRequest
import com.example.runapp.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class AuthViewModel : ViewModel() {

    // ---- LOGIN STATE ----
    private val _loginState = MutableStateFlow(AuthUiState())
    val loginState: StateFlow<AuthUiState> = _loginState

    // ---- REGISTER STATE ----
    private val _registerState = MutableStateFlow(AuthUiState())
    val registerState: StateFlow<AuthUiState> = _registerState

    // ----------------------------------------------------------
    // LOGIN
    // ----------------------------------------------------------
    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = AuthUiState(isLoading = true)

            try {
                val response = RetrofitClient.api.login(
                    LoginRequest(
                        usernameOrEmail = username,  // ← CORRIGIDO
                        password = password
                    )
                )

                if (response.isSuccessful && response.body() != null) {
                    // Podes guardar o token se quiseres
                    val token = response.body()!!.token

                    _loginState.value = AuthUiState(
                        isLoading = false,
                        success = true,
                        error = null
                    )
                } else {
                    _loginState.value = AuthUiState(
                        isLoading = false,
                        success = false,
                        error = "Credenciais inválidas (${response.code()})"
                    )
                }

            } catch (e: Exception) {
                _loginState.value = AuthUiState(
                    isLoading = false,
                    success = false,
                    error = e.message ?: "Erro ao fazer login"
                )
            }
        }
    }

    // ----------------------------------------------------------
    // REGISTO
    // ----------------------------------------------------------
    fun register(nome: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = AuthUiState(isLoading = true)

            try {
                val response = RetrofitClient.api.register(
                    RegisterRequest(
                        nome = nome,
                        username = username,
                        email = email,
                        password = password
                    )
                )

                if (response.isSuccessful) {
                    _registerState.value = AuthUiState(
                        isLoading = false,
                        success = true
                    )
                } else {
                    _registerState.value = AuthUiState(
                        isLoading = false,
                        success = false,
                        error = "Erro ao criar conta (${response.code()})"
                    )
                }

            } catch (e: Exception) {
                _registerState.value = AuthUiState(
                    isLoading = false,
                    success = false,
                    error = e.message ?: "Erro ao criar conta"
                )
            }
        }
    }

    fun clearLoginError() {
        _loginState.value = _loginState.value.copy(error = null)
    }

    fun clearRegisterError() {
        _registerState.value = _registerState.value.copy(error = null)
    }
}