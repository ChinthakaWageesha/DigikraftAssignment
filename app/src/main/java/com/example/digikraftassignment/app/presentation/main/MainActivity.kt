package com.example.digikraftassignment.app.presentation.main

import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikraftassignment.app.dependencyinjection.injectFeature
import com.example.digikraftassignment.app.domain.model.DFeatures
import com.example.digikraftassignment.app.presentation.detail_viewer.DetailsViewerActivity
import com.example.digikraftassignment.app.presentation.main.adapter.BikeStationAdapter
import com.example.digikraftassignment.core.extension.showToast
import com.example.digikraftassignment.core.extension.withNetwork
import com.example.digikraftassignment.core.google_map.GoogleMapManager
import com.example.digikraftassignment.core.presentation.BaseActivity
import com.example.digikraftassignment.core.util.IntentParcelable
import com.example.digikraftassignment.core.util.Msg
import com.example.digikraftassignment.core.util.Resource
import com.example.digikraftassignment.core.util.ResourceState
import com.example.digikraftassignment.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), (DFeatures) -> Unit {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleMapManager: GoogleMapManager
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var mLatitude: Double? = null
    private var mLongitude: Double? = null
    private val vmBikeStation by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectFeature()
        init()
    }

    private fun init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        googleMapManager = GoogleMapManager(this)
        googleMapManager.checkLocationPermission(this)
        getCurrentLocation()
        getBikeStations()
    }

    private fun getBikeStations() {
        callBikeStationService()
        vmBikeStation.liveDataBikeStations.observe(this, { observerGetBikeStations(it) })
    }

    private fun callBikeStationService() {
        withNetwork({
            vmBikeStation.getBikeStations()
        }, {
            Msg.INTERNET_ISSUE.showToast(this)
        })
    }

    private fun observerGetBikeStations(resource: Resource<List<DFeatures>>) {
        resource.let {
            when (it.state) {
                ResourceState.LOADING -> showProgress()
                ResourceState.SUCCESS -> {
                    hideProgress()
                    setBikeStationAdapter(it.features?.toMutableList())
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    it.type?.showToast(this)
                }
            }
        }
    }

    private fun setBikeStationAdapter(bikeStationList: MutableList<DFeatures>?) {
        binding.rvBikeStation.adapter =
            BikeStationAdapter(
                applicationContext,
                LatLng(mLatitude!!, mLongitude!!),
                bikeStationList,
                this
            )
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

    override fun invoke(bikeStation: DFeatures) {
        val intent = Intent(this, DetailsViewerActivity::class.java)
        intent.putExtra(IntentParcelable.LATITUDE, mLatitude)
        intent.putExtra(IntentParcelable.LONGITUDE, mLongitude)
        intent.putExtra(IntentParcelable.BIKE_STATION_DETAILS, bikeStation)
        startActivity(intent)
    }

}