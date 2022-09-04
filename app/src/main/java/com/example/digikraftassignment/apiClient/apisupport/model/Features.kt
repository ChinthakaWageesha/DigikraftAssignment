package com.example.digikraftassignment.apiClient.apisupport.model

import com.squareup.moshi.Json

data class Features(
    @Json(name = "geometry") @field:Json(name = "geometry") var geometry: Geometry? = null,
    @Json(name = "id") @field:Json(name = "id") var id: String? = null,
    @Json(name = "type") @field:Json(name = "type") var type: String? = null,
    @Json(name = "properties") @field:Json(name = "properties") var bikeStationProperties: BikeStationProperties? = null,
)
