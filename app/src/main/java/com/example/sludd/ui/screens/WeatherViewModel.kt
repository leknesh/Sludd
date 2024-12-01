package com.example.sludd.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sludd.data.CurrentWeather
import com.example.sludd.data.WeatherRepository
import com.example.sludd.data.toCurrentWeather
import com.example.sludd.location.LocationProvider
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationProvider: LocationProvider
) : ViewModel() {

    private val osloLat = 59.91
    private val osloLong = 10.75

    var uiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    fun updateWeather() {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            uiState = try {
                val location = locationProvider.getCurrentLocation()
                val response = if (location != null) {
                    weatherRepository.getCurrentWeather(location.latitude, location.longitude)
                } else {
                    weatherRepository.getCurrentWeather(osloLat, osloLong)
                }

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
