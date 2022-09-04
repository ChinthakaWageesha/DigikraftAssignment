package com.example.digikraftassignment.app.data.datasource

import com.example.digikraftassignment.app.domain.model.DFeatures
import io.reactivex.Single

interface BikeStationDataSource {

    fun getBikeStationData(): Single<List<DFeatures>>

}