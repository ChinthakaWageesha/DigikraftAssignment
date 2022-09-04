package com.example.digikraftassignment.app.data.repository

import com.example.digikraftassignment.app.data.datasource.BikeStationDataSource
import com.example.digikraftassignment.app.domain.model.DFeatures
import com.example.digikraftassignment.app.domain.repository.BikeStationRepository
import io.reactivex.Single

class BikeStationRepositoryImpl constructor(
    private val bikeStationDataSource: BikeStationDataSource
) : BikeStationRepository{

    override fun getBikeStations(): Single<List<DFeatures>> =
        bikeStationDataSource.getBikeStationData()

}