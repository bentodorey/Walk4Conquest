package com.example.runapp.network

class ApiModelspackage

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String // igual ao que o backend devolve
)

// REGISTO
data class RegisterRequest(
    val nome: String,
    val username: String,
    val email: String,
    val password: String,
    val sexo: String? = null,
    val alturaCm: Double? = null,
    val pesoKg: Double? = null,
    val dataNascimento: String? = null // "2000-01-01"
)
{
}