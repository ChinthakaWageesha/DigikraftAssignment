package com.example.digikraftassignment.app.remotedatasource

import com.example.digikraftassignment.apiClient.apis.BikeStationApi
import com.example.digikraftassignment.app.data.datasource.BikeStationDataSource
import com.example.digikraftassignment.app.dependencyinjection.mapToDomain
import com.example.digikraftassignment.app.domain.model.DFeatures
import io.reactivex.Single

class BikeStationRemoteDataSourceImpl(
    private val bikeStationApi: BikeStationApi
) : BikeStationDataSource {

    override fun getBikeStationData(): Single<List<DFeatures>> =
        bikeStationApi.getBikeStations(
            mType = "pub_transport",
            co = "stacje_rowerowe"
        ).map {
            it.features?.map {
                it.mapToDomain()
            }
        }

}