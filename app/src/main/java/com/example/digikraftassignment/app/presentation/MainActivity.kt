package com.example.digikraftassignment.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.digikraftassignment.R
import com.example.digikraftassignment.app.dependencyinjection.injectFeature
import com.example.digikraftassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        injectFeature()
        setBikeStationAdapter()
    }

    private fun setBikeStationAdapter() {
        binding.rvBikeStation.adapter = BikeStationAdapter(applicationContext)
        binding.rvBikeStation.layoutManager = LinearLayoutManager(applicationContext)
    }

}