package com.example.sludd.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sludd.R
import com.example.sludd.data.ForecastWeather
import com.example.sludd.data.WeatherInfo
import com.example.sludd.data.getDrawable

@Composable
fun WeatherCardView(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        content()
    }
}

@Composable
fun CurrentWeatherCard(weather: WeatherInfo, placeName: String? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Current Weather",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = if (placeName != null) {
                "Location: $placeName"
            } else {
                "Location: ${weather.latitude}, ${weather.longitude}"
            },
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = weather.currentWeather.weatherCode.getDrawable()
                ),
                contentDescription = "weather icon",
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = weather.currentWeather.weatherCode.description,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = weather.currentWeather.temperature,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Humidity: ${weather.currentWeather.humidity}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Wind Speed: ${weather.currentWeather.windSpeed}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun SingleDayWeatherCard(forecastWeather: ForecastWeather, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = forecastWeather.time,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = forecastWeather.weatherCode.getDrawable()),
            contentDescription = "weather icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Max: ${forecastWeather.temperatureMax}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Min: ${forecastWeather.temperatureMin}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun QueryCard(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Row(modifier = modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            label = { Text(stringResource(R.string.search_location)) },
            singleLine = true
        )
        Button(
            onClick = {
                focusManager.clearFocus()
                onSearch()
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = stringResource(R.string.search)
            )
        }
    }
}