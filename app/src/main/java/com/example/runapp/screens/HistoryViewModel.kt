package com.example.runapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.runapp.network.RetrofitClient
import com.example.runapp.utils.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    private val _percursos = MutableStateFlow<List<PercursoItem>>(emptyList())
    val percursos: StateFlow<List<PercursoItem>> = _percursos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val context = getApplication<Application>().applicationContext
                val token = TokenManager.getToken(context)

                if (token != null) {
                    val profile = RetrofitClient.apiService.getUserProfile("Bearer $token")
                    val userId = profile.id

                    val response = RetrofitClient.apiService.getHistoricoPercursos(userId)

                    // Converter a lista de PercursoResponse para PercursoItem
                    val items = response.map { percurso ->
                        PercursoItem(
                            id = percurso.id,
                            distanciaKm = percurso.distanciaKm,
                            duracaoMin = percurso.duracaoMin,
                            dataFim = percurso.dataFim,
                            calorias = percurso.calorias,
                            passos = percurso.passos,
                            velocidadeMediaKmh = percurso.velocidadeMediaKmh
                        )
                    }

                    _percursos.value = items
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _percursos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}

data class PercursoItem(
    val id: Long,
    val distanciaKm: Double?,
    val duracaoMin: Int?,
    val dataFim: String?,
    val calorias: Int?,
    val passos: Int?,
    val velocidadeMediaKmh: Double?
)