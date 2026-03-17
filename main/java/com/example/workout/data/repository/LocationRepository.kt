package com.example.workout.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class LocationRepository(private val fusedLocationClient: FusedLocationProviderClient) {

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getNetworkLocation(): Pair<Double, Double>? {
        return try {
            val location = fusedLocationClient.lastLocation.await()
            location?.latitude?.let { lat ->
                location.longitude?.let { lon ->
                    lat to lon
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}