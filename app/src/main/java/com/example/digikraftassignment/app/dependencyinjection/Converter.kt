package com.example.digikraftassignment.app.dependencyinjection

import com.example.digikraftassignment.apiClient.apisupport.model.BikeStationProperties
import com.example.digikraftassignment.apiClient.apisupport.model.Features
import com.example.digikraftassignment.apiClient.apisupport.model.Geometry
import com.example.digikraftassignment.app.domain.model.DBikeStationProperties
import com.example.digikraftassignment.app.domain.model.DFeatures
import com.example.digikraftassignment.app.domain.model.DGeometry

fun Features.mapToDomain(): DFeatures = DFeatures(
    geometry = geometry?.mapToDomain(),
    id = id,
    type = type,
    bikeStationProperties = bikeStationProperties?.mapToDomain()
)

fun Geometry.mapToDomain(): DGeometry = DGeometry(
    coordinates = coordinates,
    type = type
)

fun BikeStationProperties.mapToDomain(): DBikeStationProperties = DBikeStationProperties(
    freeRacks = freeRacks,
    bikes = availableBikes,
    lable = bikeStationName,
    bikeRacks = allBikes,
    updated = updatedAt
)