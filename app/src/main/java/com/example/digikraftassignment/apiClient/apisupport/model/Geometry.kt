package com.example.digikraftassignment.apiClient.apisupport.model

import com.squareup.moshi.Json

data class Geometry(
    @Json(name = "coordinates") @field:Json(name = "coordinates") var coordinates: ArrayList<Double>? = null,
    @Json(name = "type") @field:Json(name = "type") var type: String? = null
)
