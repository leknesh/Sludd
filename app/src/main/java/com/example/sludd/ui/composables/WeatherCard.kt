package com.example.sludd.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeatherCardView(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    CardView(modifier = modifier) {
        content()
    }
}

@Composable
fun CardView(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        content()
    }
}