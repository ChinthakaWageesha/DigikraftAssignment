package com.example.digikraftassignment.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.digikraftassignment.navigation.MainNavigation

class CoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMain()
    }

    private fun startMain() = startActivity(MainNavigation.dynamicStart).also {
        finish()
    }
}