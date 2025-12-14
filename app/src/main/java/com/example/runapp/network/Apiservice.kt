package com.example.runapp.network

import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>

    @GET("auth/me")
    suspend fun getUserProfile(
        @Header("Authorization") token: String
    ): UserProfileResponse

    @GET("percurso/historico")
    suspend fun getHistoricoPercursos(
        @Query("utilizadorId") utilizadorId: Long
    ): List<PercursoResponse>

    @GET("leaderboard")
    suspend fun getLeaderboard(): List<LeaderboardItem>
}