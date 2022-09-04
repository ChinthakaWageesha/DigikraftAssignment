package com.example.digikraftassignment.app.presentation.detail_viewer

import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import com.example.digikraftassignment.R
import com.example.digikraftassignment.core.google_map.GoogleMapCallback
import com.example.digikraftassignment.core.google_map.GoogleMapManager
import com.example.digikraftassignment.core.presentation.BaseActivity
import com.example.digikraftassignment.core.util.IntentParcelable
import com.example.digikraftassignment.databinding.ActivityDetailsViewerBinding
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class DetailsViewerActivity : BaseActivity(), GoogleMapCallback {

    private lateinit var binding: ActivityDetailsViewerBinding
    private lateinit var googleMapManager: GoogleMapManager
    private var mLatitude: Double? = null
    private var mLongitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        getData()
        setMap()
        setData()
    }

    private fun getData() {
        mLatitude = intent.getDoubleExtra(IntentParcelable.LATITUDE, 0.0)
        mLongitude = intent.getDoubleExtra(IntentParcelable.LONGITUDE, 0.0)
    }

    private fun setToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setMap() {
        val userLatLng = LatLng(mLatitude!!, mLongitude!!)
        val bikeStationLatLng = LatLng(6.9061, 79.9696)
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_bike_station) as SupportMapFragment
        googleMapManager = GoogleMapManager(this)
        googleMapManager.checkLocationPermission(this)
        googleMapManager.initializeMap(mapFragment, userLatLng, bikeStationLatLng)
        googleMapManager.setMapCallback(this)
    }


    private fun setData() {
        binding.bikeStationDetails.tvBikeStationName.text = "15589 Ofiar Dabia"
        binding.bikeStationDetails.tvBikeStationDistance.text = "800m"
        binding.bikeStationDetails.tvTitleBikeStation.text = getString(R.string.label_bike_station)
        binding.bikeStationDetails.tvAvailableBikes.text = "7"
        binding.bikeStationDetails.tvAvailablePlaces.text = "21"
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}