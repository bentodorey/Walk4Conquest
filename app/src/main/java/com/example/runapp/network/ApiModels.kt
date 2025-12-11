package com.example.runapp.network

data class LoginRequest(
    val usernameOrEmail: String,  // ← SÓ ESTE CAMPO (remove o username)
    val password: String
)

data class LoginResponse(
    val token: String,
    val message: String
)

// REGISTO – agora com todos os campos
data class RegisterRequest(
    val nome: String,
    val username: String,
    val email: String,
    val password: String,
    val sexo: String? = null,
    val alturaCm: Double? = null,
    val pesoKg: Double? = null,
    val dataNascimento: String? = null
)