package com.example.digikraftassignment.app.presentation.main

import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikraftassignment.app.dependencyinjection.injectFeature
import com.example.digikraftassignment.app.presentation.detail_viewer.DetailsViewerActivity
import com.example.digikraftassignment.core.google_map.GoogleMapManager
import com.example.digikraftassignment.core.presentation.BaseActivity
import com.example.digikraftassignment.core.util.IntentParcelable
import com.example.digikraftassignment.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : BaseActivity(), (String) -> Unit {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMapManager: GoogleMapManager
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude: Double? = null
    private var mLongitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectFeature()
        init()
        setBikeStationAdapter()
    }

    private fun init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        googleMapManager = GoogleMapManager(this)
        googleMapManager.checkLocationPermission(this)
        getCurrentLocation()
    }

    private fun setBikeStationAdapter() {
        binding.rvBikeStation.adapter = BikeStationAdapter(applicationContext, this)
        binding.rvBikeStation.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun getCurrentLocation() {
        if (googleMapManager.checkLocationPermission(this)) {
            mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                val location: Location? = task.result
                if (location != null) {
                    mLatitude = location.latitude
                    mLongitude = location.longitude
                }
            }
        }
    }

    override fun invoke(p1: String) {
        val intent = Intent(this, DetailsViewerActivity::class.java)
        intent.putExtra(IntentParcelable.LATITUDE, mLatitude)
        intent.putExtra(IntentParcelable.LONGITUDE, mLongitude)
        startActivity(intent)
    }

}