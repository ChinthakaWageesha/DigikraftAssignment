package com.example.digikraftassignment.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DFeatures(
    var geometry: DGeometry? = null,
    var id: String? = null,
    var type: String? = null,
    var bikeStationProperties: DBikeStationProperties? = null,
): Parcelable
