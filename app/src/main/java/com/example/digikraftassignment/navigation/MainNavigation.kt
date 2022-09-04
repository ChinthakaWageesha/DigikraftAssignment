package com.example.digikraftassignment.navigation

import android.content.Intent

object MainNavigation: DynamicFeature<Intent> {

    private const val MAIN = "com.example.digikraftassignment.app.presentation.MainActivity"

    override val dynamicStart: Intent?
        get() = MAIN.loadIntent()
}