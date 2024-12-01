package com.example.sludd.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sludd.R
import com.example.sludd.data.CurrentWeather
import com.example.sludd.ui.composables.WeatherCardView

@Composable
fun WeatherScreen(
    weatherUiState: WeatherUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (weatherUiState) {
            is WeatherUiState.Loading -> LoadingScreen(modifier)
            is WeatherUiState.Loaded -> ResultScreen(
                weatherUiState.result,
                modifier.padding(top = contentPadding.calculateTopPadding())
            )

            is WeatherUiState.Error -> ErrorScreen(modifier)
        }
    }
}

@Composable
fun ResultScreen(weather: CurrentWeather?, modifier: Modifier = Modifier) {
    WeatherCardView {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Spacer(Modifier.height(16.dp))
            Text(text = "Current weather")
            Text(text = "${weather?.temperature}")
            Text(text = "${weather?.humidity}")
            Text(text = "${weather?.windSpeed}")
            Text(text = "${weather?.description}")
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
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
            text = stringResource(R.string.network_error),
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


