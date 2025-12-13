package com.example.runapp.network

import com.google.gson.annotations.SerializedName

// Login
data class LoginRequest(
    val usernameOrEmail: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val message: String
)

// Register
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

// User Profile
data class UserProfileResponse(
    val id: Long,
    val nome: String,
    val username: String,
    val email: String,
    val sexo: String?,
    val alturaCm: Double?,
    val pesoKg: Double?,
    val dataNascimento: String?,
    val pontos: Int,
    val totalDistanciaKm: Double?,
    val totalCorridas: Int?,
    val totalTerritoriosConquistados: Int?,
    val nivel: Int?,
    val experiencia: Int?
)

// Percurso (Histórico)
data class PercursoResponse(
    val id: Long,
    val utilizadorId: Long,
    val utilizadorNome: String,
    val dataInicio: String,
    val dataFim: String?,
    val distanciaKm: Double?,
    val duracaoMin: Int?,
    val calorias: Int?,
    val passos: Int?,
    val ritmoMedio: String?,
    val velocidadeMediaKmh: Double?,
    val estado: String
)