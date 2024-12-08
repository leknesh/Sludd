package com.example.sludd.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sludd.data.WeatherInfo
import com.example.sludd.data.WeatherRepository
import com.example.sludd.location.GeocoderService
import com.example.sludd.location.LocationProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepository: WeatherRepository,
    private val locationProvider: LocationProvider,
    private val geocoderService: GeocoderService
) : ViewModel() {

    private val osloLat = 59.91
    private val osloLong = 10.75

    var uiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    // Oslo used as default location
    var locationState: LocationState by mutableStateOf(LocationState(osloLat, osloLong))
        private set

    private val _placeNameState = MutableStateFlow("")
    val placeNameState: StateFlow<String> get() = _placeNameState

    fun updateLocation() {
        viewModelScope.launch {
            try {
                val location = locationProvider.getCurrentLocation()
                if (location != null) {
                    locationState = LocationState(location.latitude, location.longitude)
                }
            } catch (e: Exception) {
                Log.d("WeatherTag", "Location error: $e")
            }
        }
    }

    fun searchLocation(query: String) {
        viewModelScope.launch {
            try {
                val address = geocoderService.getAddress(query)
                if (address != null) {
                    locationState = LocationState(address.latitude, address.longitude)
                    _placeNameState.value = address.getAddressLine(0)
                    updateWeather()
                }
            } catch (e: Exception) {
                Log.d("WeatherTag", "Search error: $e")
            }
        }
    }

    fun updateWeather() {
        viewModelScope.launch {
            uiState = try {
                val weather = weatherRepository.getWeather(locationState.latitude, locationState.longitude)
                WeatherUiState.Loaded(weather)
            } catch (e: Exception) {
                Log.d("WeatherTag", "Load error: $e")
                WeatherUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

data class LocationState(val latitude: Double, val longitude: Double)

sealed interface WeatherUiState {
    data class Loaded(val current: WeatherInfo) : WeatherUiState
    data class Error(val message: String) : WeatherUiState
    object Loading : WeatherUiState
}
