package com.example.digikraftassignment.core.util

import android.app.Application
import com.example.digikraftassignment.network.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    apiModule
                )
            )
        }
    }

}