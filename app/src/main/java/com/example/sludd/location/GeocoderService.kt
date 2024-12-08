package com.example.sludd.location

import android.location.Address
import android.location.Geocoder
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class GeocoderService(
    private val geocoder: Geocoder
) {
    suspend fun getAddress(query: String): Address? = withContext(Dispatchers.IO) {
        try {
            val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                suspendCancellableCoroutine<List<Address>> { continuation ->
                    geocoder.getFromLocationName(query, 1) { addresses ->
                        continuation.resume(addresses)
                    }
                }
            } else {
                @Suppress("DEPRECATION")
                geocoder.getFromLocationName(query, 1)
            }
            // For now just using the first query result, should be expanded to a dropdown of suggestions
            // that updates as you type
            addresses?.firstOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

