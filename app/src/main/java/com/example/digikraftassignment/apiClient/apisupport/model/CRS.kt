package com.example.digikraftassignment.apiClient.apisupport.model

import com.squareup.moshi.Json

data class CRS(
    @Json(name = "type") @field:Json(name = "type") var type: String? = null,
    @Json(name = "properties") @field:Json(name = "properties") var properties: Properties? = null,
)
