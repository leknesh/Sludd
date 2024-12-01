package com.example.sludd.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sludd.data.CurrentWeather
import com.example.sludd.data.toCurrentWeather
import com.example.sludd.network.WeatherApiManager
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    var uiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    init {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            uiState = try {
                val response = WeatherApiManager.getCurrentWeather(23.35, 2.43)
                val currentWeather = response.toCurrentWeather()
                WeatherUiState.Loaded(result = currentWeather)
            } catch (e: Exception) {
                Log.d("WeatherTag", "Loaderror: $e")
                WeatherUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed interface WeatherUiState {
    data class Loaded(val result: CurrentWeather?) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    object Loading : WeatherUiState
}