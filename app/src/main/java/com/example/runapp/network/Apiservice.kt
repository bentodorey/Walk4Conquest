package com.example.runapp.network

import retrofit2.Response
import retrofit2.http.*

// 2 Usages
interface ApiService {

    // 1 Usage
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // 1 Usage
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>

    // NOVO: Buscar perfil do utilizador autenticado
    @GET("auth/me")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): Response<UserProfileResponse>
}