package com.example.runapp.network

import com.google.gson.annotations.SerializedName

// 3 Usages
data class LoginRequest(
    val usernameOrEmail: String,
    val password: String
)

// 1 Usage
data class LoginResponse(
    val token: String,
    val message: String
)

// REGISTO - agora com todos os campos
// 3 Usages
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
data class UserProfileResponse(
    val id: Long,
    val nome: String,
    val username: String,
    val email: String,
    val sexo: String?,
    val alturaCm: Double?,
    val pesoKg: Double?,
    val dataNascimento: String?,
    val pontos: Int
)

//