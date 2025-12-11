package com.example.runapp.network

// LOGIN  (igual ao LoginRequest.java do backend)
data class LoginRequest(
    val username: String,
    val password: String
)

// o backend devolve um TokenResponse { "token": "..." }
data class LoginResponse(
    val token: String
)

// REGISTO  (igual ao RegisterRequest.java do backend)
data class RegisterRequest(
    val username: String,
    val password: String
)
