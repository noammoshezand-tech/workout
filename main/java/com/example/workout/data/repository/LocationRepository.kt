package com.example.workout.data.repository

import android.Manifest
import android.location.Location
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import kotlinx.coroutines.tasks.await

class LocationRepository(private val fusedLocationClient: FusedLocationProviderClient) {

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getNetworkLocation(): Pair<Double, Double>? {
        return try {
            var location: Location? = fusedLocationClient.lastLocation.await()
            
            if (location == null) {
                val request = CurrentLocationRequest.Builder()
                    .setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
                    .build()
                location = fusedLocationClient.getCurrentLocation(request, null).await()
            }

            location?.let {
                it.latitude to it.longitude
            }
        } catch (e: Exception) {
            null
        }
    }
}