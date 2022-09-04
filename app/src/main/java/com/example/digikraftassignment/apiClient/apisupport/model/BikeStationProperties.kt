package com.example.digikraftassignment.apiClient.apisupport.model

import com.squareup.moshi.Json

data class BikeStationProperties(
    @Json(name = "free_racks") @field:Json(name = "free_racks") var freeRacks: String? = null,
    @Json(name = "bikes") @field:Json(name = "bikes") var availableBikes: String? = null,
    @Json(name = "label") @field:Json(name = "label") var bikeStationName: String? = null,
    @Json(name = "bike_racks") @field:Json(name = "bike_racks") var allBikes: String? = null,
    @Json(name = "updated") @field:Json(name = "updated") var updatedAt: String? = null,
)
