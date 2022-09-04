package com.example.digikraftassignment.core.google_map

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.digikraftassignment.core.extension.bitmapFromVector
import com.example.digikraftassignment.core.extension.showToast
import com.example.digikraftassignment.core.util.RequestCode
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import java.util.*

class GoogleMapManager(context: Context) : OnMapReadyCallback {

    companion object {
        const val DEFAULT_ZOOM = 15
    }

    private lateinit var mMapView: SupportMapFragment
    private lateinit var mGoogleMap: GoogleMap
    private var mGoogleMapCallback: GoogleMapCallback? = null
    private var mContext = context
    private var currentLatLng: LatLng? = null
    private var bikeStationLatLng: LatLng? = null

    fun setMapCallback(googleMapCallback: GoogleMapCallback) {
        this.mGoogleMapCallback = googleMapCallback
    }

    fun initializeMap(mapFragment: SupportMapFragment, userLatLng: LatLng, stationLatLng: LatLng) {
        mMapView = mapFragment
        currentLatLng = userLatLng
        bikeStationLatLng = stationLatLng
        mMapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.mGoogleMap = googleMap
        mGoogleMap.uiSettings.isMyLocationButtonEnabled = false
        setCamera(currentLatLng!!, bikeStationLatLng!!)
        mGoogleMap.setOnCameraIdleListener { mGoogleMapCallback?.onCameraMove() }
        mGoogleMapCallback?.mapOnReadyCallback(mGoogleMap)

    }

    fun checkLocationPermission(activity: Activity): Boolean {
        return if (ContextCompat.checkSelfPermission(
                activity.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    RequestCode.LOCATION_REQUEST_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    RequestCode.LOCATION_REQUEST_CODE
                )
            }
            false
        } else {
            true
        }
    }

    private fun setCamera(userLatLng: LatLng, stationLatLng: LatLng) {
        val cameraPosition = CameraPosition.Builder()
            .target(userLatLng)
            .zoom(DEFAULT_ZOOM.toFloat())
            .build()

        setCurrentLocationMarker(userLatLng)
        setStationLocationMarker(stationLatLng)
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun setCurrentLocationMarker(latLng: LatLng) {

        val markerOptions = MarkerOptions().apply {
            position(latLng)
            icon(
                com.example.digikraftassignment.R.drawable.ic_current_location.bitmapFromVector(
                    mContext
                )
            )
        }
        mGoogleMap.addMarker(markerOptions)
    }

    private fun setStationLocationMarker(latLng: LatLng) {

        val markerOptions = MarkerOptions().apply {
            position(latLng)
            icon(
                com.example.digikraftassignment.R.drawable.ic_bike.bitmapFromVector(
                    mContext
                )
            )
        }
        mGoogleMap.addMarker(markerOptions)
    }
}