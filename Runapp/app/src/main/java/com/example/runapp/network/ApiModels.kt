package com.example.runapp.network

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String
)

// REGISTO – agora com todos os campos
data class RegisterRequest(
    val nome: String,
    val username: String,
    val email: String,
    val password: String
)
