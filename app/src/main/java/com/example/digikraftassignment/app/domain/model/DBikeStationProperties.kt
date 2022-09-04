package com.example.digikraftassignment.app.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DBikeStationProperties(
    var freeRacks: String? = null,
    var bikes: String? = null,
    var lable: String? = null,
    var bikeRacks: String? = null,
    var updated: String? = null,
): Parcelable
