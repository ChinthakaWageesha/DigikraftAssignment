package com.example.digikraftassignment.app.domain.repository

import com.example.digikraftassignment.app.domain.model.DFeatures
import io.reactivex.Single

interface BikeStationRepository {

    fun getBikeStations(): Single<List<DFeatures>>

}