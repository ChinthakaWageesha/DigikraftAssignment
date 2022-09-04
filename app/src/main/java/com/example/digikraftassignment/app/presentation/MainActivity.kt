package com.example.digikraftassignment.app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digikraftassignment.R
import com.example.digikraftassignment.app.dependencyinjection.injectFeature

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectFeature()
    }
}