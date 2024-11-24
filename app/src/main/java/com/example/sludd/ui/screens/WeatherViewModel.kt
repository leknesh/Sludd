package com.example.sludd.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sludd.data.WeatherRepository
import kotlinx.coroutines.launch
import java.io.IOException

class WeatherViewModel(
   private val weatherRepository: WeatherRepository
) : ViewModel() {

    init {
        getCurrentWeather()
    }

    var uiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    private fun getCurrentWeather() {
        viewModelScope.launch {
            uiState = try {
                val currentWeather = weatherRepository.getCurrentWeather()
                WeatherUiState.Loaded("Weather received: $currentWeather")
            } catch (e: Exception) {
                WeatherUiState.Error
            }
        }
    }
}

sealed interface WeatherUiState {
    data class Loaded(val result: String) : WeatherUiState
    object Error : WeatherUiState
    object Loading : WeatherUiState
}