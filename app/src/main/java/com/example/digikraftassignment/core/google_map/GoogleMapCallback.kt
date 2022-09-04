package com.example.digikraftassignment.core.google_map

import com.google.android.gms.maps.GoogleMap

interface GoogleMapCallback {

    fun mapOnReadyCallback(googleMap: GoogleMap) {

    }

    fun onCameraMove() {

    }

}