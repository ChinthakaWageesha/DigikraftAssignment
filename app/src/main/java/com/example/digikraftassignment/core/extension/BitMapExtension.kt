package com.example.digikraftassignment.core.extension

import android.content.Context
import com.google.android.gms.maps.model.BitmapDescriptorFactory

import android.graphics.Bitmap
import android.graphics.Canvas

import androidx.core.content.ContextCompat

import android.graphics.drawable.Drawable

import com.google.android.gms.maps.model.BitmapDescriptor


fun Int.bitmapFromVector(context: Context): BitmapDescriptor? {

    val vectorDrawable = ContextCompat.getDrawable(context, this)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

    val bitmap = Bitmap.createBitmap(
        vectorDrawable.intrinsicWidth,
        vectorDrawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)

    return BitmapDescriptorFactory.fromBitmap(bitmap)
}