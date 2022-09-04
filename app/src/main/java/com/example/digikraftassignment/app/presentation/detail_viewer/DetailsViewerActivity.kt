package com.example.digikraftassignment.app.presentation.detail_viewer

import android.location.Location
import android.os.Bundle
import android.view.MenuItem
import com.example.digikraftassignment.R
import com.example.digikraftassignment.app.domain.model.DFeatures
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
    private var bikeStation: DFeatures? = null

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
        bikeStation = intent.getParcelableExtra(IntentParcelable.BIKE_STATION_DETAILS)
    }

    private fun setToolbar() {
        if (supportActionBar != null) {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setMap() {
        val userLatLng = LatLng(mLatitude!!, mLongitude!!)
        val bikeStationLatLng =
            bikeStation?.geometry?.coordinates?.get(0)?.let {
                LatLng(
                    it,
                    bikeStation?.geometry?.coordinates?.get(1)!!
                )
            }
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_bike_station) as SupportMapFragment
        googleMapManager = GoogleMapManager(this)
        googleMapManager.checkLocationPermission(this)
        googleMapManager.initializeMap(mapFragment, userLatLng, bikeStationLatLng!!)
        googleMapManager.setMapCallback(this)
    }


    private fun setData() {
        binding.bikeStationDetails.tvBikeStationName.text =
            bikeStation?.id.toString().plus(" ${bikeStation?.bikeStationProperties?.lable}")
        binding.bikeStationDetails.tvBikeStationDistance.text =
            bikeStation?.let { getDistance(it).toString().plus("m") }
        binding.bikeStationDetails.tvTitleBikeStation.text = getString(R.string.label_bike_station)
        binding.bikeStationDetails.tvAvailableBikes.text = bikeStation?.bikeStationProperties?.bikes
        binding.bikeStationDetails.tvAvailablePlaces.text = bikeStation?.bikeStationProperties?.freeRacks
    }

    private fun getDistance(bikeStation: DFeatures) : Float {
        val startPoint = Location("userLocation")
        startPoint.latitude = mLatitude!!
        startPoint.longitude = mLongitude!!

        val endPoint = Location("bikeStationLocation")
        endPoint.latitude = bikeStation.geometry?.coordinates?.get(0)!!
        endPoint.longitude = bikeStation.geometry?.coordinates?.get(1)!!

        return startPoint.distanceTo(endPoint)
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