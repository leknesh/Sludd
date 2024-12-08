package com.example.sludd.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sludd.R
import com.example.sludd.data.WeatherInfo
import com.example.sludd.ui.composables.CurrentWeatherCard
import com.example.sludd.ui.composables.QueryCard
import com.example.sludd.ui.composables.SingleDayWeatherCard
import com.example.sludd.ui.composables.WeatherCardView

@Composable
fun WeatherScreen(
    weatherUiState: WeatherUiState,
    onSearch: (String) -> Unit,
    placeName: String? = null,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    var query by rememberSaveable { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        when (weatherUiState) {
            is WeatherUiState.Loading -> LoadingScreen(modifier)
            is WeatherUiState.Loaded -> ResultScreen(
                weatherUiState.current,
                query,
                placeName,
                onQueryChange = { query = it },
                onSearch = { onSearch(query) },
                modifier.padding(top = contentPadding.calculateTopPadding())
            )

            is WeatherUiState.Error -> ErrorScreen(modifier, weatherUiState.message)
        }
    }
}

@Composable
fun ResultScreen(
    weather: WeatherInfo,
    query: String,
    placeName: String?,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier) {
        item {
            WeatherCardView {
                QueryCard(query, onQueryChange, onSearch)
            }
        }
        item {
            WeatherCardView {
                CurrentWeatherCard(weather, placeName)
            }
        }
        items(weather.foreCast) { forecastWeather ->
            WeatherCardView {
               SingleDayWeatherCard(forecastWeather)
            }
        }
    }
}


@Composable
fun ErrorScreen(modifier: Modifier, message: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_error_24),
            contentDescription = "error"
        )
        Text(
            text = message,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize(1f),
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant

        )
    }
}