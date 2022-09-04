package com.example.digikraftassignment.app.presentation.main

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikraftassignment.app.dependencyinjection.injectFeature
import com.example.digikraftassignment.core.general.GoTo
import com.example.digikraftassignment.core.presentation.BaseActivity
import com.example.digikraftassignment.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), (String) -> Unit {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectFeature()
        setBikeStationAdapter()
    }

    private fun setBikeStationAdapter() {
        binding.rvBikeStation.adapter = BikeStationAdapter(applicationContext, this)
        binding.rvBikeStation.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun invoke(p1: String) {
        GoTo.detailsViewer(this)
    }

}