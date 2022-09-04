package com.example.digikraftassignment.apiClient.apisupport.response

import com.example.digikraftassignment.apiClient.apisupport.model.CRS
import com.example.digikraftassignment.apiClient.apisupport.model.Features
import com.squareup.moshi.Json

data class BikeStationApiResponse(
    @Json(name = "features") @field:Json(name = "features") var features: List<Features>? = null,
    @Json(name = "crs") @field:Json(name = "crs") var crs: CRS? = null,
    @Json(name = "type") @field:Json(name = "type") var type: String? = null,
)
