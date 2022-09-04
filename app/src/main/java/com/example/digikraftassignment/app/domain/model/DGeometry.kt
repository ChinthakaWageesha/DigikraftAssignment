package com.example.digikraftassignment.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DGeometry(
    var coordinates: ArrayList<Double>? = null,
    var type: String? = null
): Parcelable
