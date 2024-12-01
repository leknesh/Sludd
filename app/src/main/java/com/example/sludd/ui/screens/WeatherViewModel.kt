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
                val response = WeatherApiManager.getCurrentWeather(52.52, 13.41)
                Log.d("WeatherTag", "Response: $response")
                val currentWeather = response.toCurrentWeather()
                Log.d("WeatherTag", "Got currentweather: $currentWeather")
                WeatherUiState.Loaded(result = currentWeather)
            } catch (e: Exception) {
                Log.d("WeatherTag", "Loaderror: $e")
                WeatherUiState.Error
            }
        }
    }
}

sealed interface WeatherUiState {
    data class Loaded(val result: CurrentWeather?) : WeatherUiState
    object Error : WeatherUiState
    object Loading : WeatherUiState
}