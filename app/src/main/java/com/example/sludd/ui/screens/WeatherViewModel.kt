package com.example.sludd.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sludd.network.RetrofitInstance
import com.example.sludd.network.WeatherInfo
import com.example.sludd.network.extractCurrentWeatherInfo
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
                val response = RetrofitInstance.api.getCurrentWeather(latitude = 59.8, longitude = 10.0)
                val weatherInfo = extractCurrentWeatherInfo(response)
                Log.d("Weather", "Got weather: $response")
                WeatherUiState.Loaded(result = weatherInfo)
            } catch (e: Exception) {
                Log.d("Weather", "Loaderror: $e")
                WeatherUiState.Error
            }
        }
    }
}

sealed interface WeatherUiState {
    data class Loaded(val result: WeatherInfo?) : WeatherUiState
    object Error : WeatherUiState
    object Loading : WeatherUiState
}