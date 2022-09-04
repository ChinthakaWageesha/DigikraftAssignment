package com.example.digikraftassignment.apiClient.apis

import com.example.digikraftassignment.apiClient.apisupport.response.BikeStationApiResponse
import io.reactivex.Single
import retrofit2.http.GET

interface BikeStationApi {

    @GET("map_service.html")
    fun getBikeStations(
        @retrofit2.http.Query("mtype") mType: String,
        @retrofit2.http.Query("co") co: String
    ): Single<BikeStationApiResponse>
}