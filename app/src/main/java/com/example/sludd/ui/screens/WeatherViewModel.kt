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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

private val osloDefaultState = LocationState(59.91, 10.75)

class WeatherViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    var uiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    private val _locationState = MutableStateFlow(LocationState())
    val locationState: StateFlow<LocationState> = _locationState

    fun updateWeather() {
        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        viewModelScope.launch {
            uiState = try {
                val response = weatherRepository.getCurrentWeather(23.35, 2.43)
                val currentWeather = response.toCurrentWeather()
                WeatherUiState.Loaded(result = currentWeather)
            } catch (e: Exception) {
                Log.d("WeatherTag", "Loaderror: $e")
                WeatherUiState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun getLocation() {
        _locationState.value = osloDefaultState
    }

    fun updateLocation(lat: Double, long: Double) {
        _locationState.value = LocationState(lat, long)
    }
}

data class LocationState (
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

sealed interface WeatherUiState {
    data class Loaded(val result: CurrentWeather?) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    object Loading : WeatherUiState
}
