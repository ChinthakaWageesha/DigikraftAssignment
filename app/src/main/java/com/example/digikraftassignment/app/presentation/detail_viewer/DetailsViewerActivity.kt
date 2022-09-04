package com.example.digikraftassignment.app.presentation.detail_viewer

import android.os.Bundle
import android.view.MenuItem
import com.example.digikraftassignment.R
import com.example.digikraftassignment.core.presentation.BaseActivity
import com.example.digikraftassignment.databinding.ActivityDetailsViewerBinding

class DetailsViewerActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setData()
    }

    private fun setToolbar(){
        if (supportActionBar != null){
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_white)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setData(){
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