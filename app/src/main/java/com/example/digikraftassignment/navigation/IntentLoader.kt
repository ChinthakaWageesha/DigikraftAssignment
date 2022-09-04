package com.example.digikraftassignment.navigation

import android.content.Intent

private const val PACKAGE_NAME = "com.example.digikraftassignment"

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntent(): Intent? =
    try {
        Class.forName(this).run {
            intentTo(this@loadIntent)
        }
    } catch (e: ClassNotFoundException){
        null
    }