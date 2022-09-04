package com.example.digikraftassignment.network

import com.example.digikraftassignment.apiClient.apis.BikeStationApi
import com.example.digikraftassignment.core.util.Constant
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

private val supportInterceptor = SupportInterceptor()

private val retrofit: Retrofit =
    createNetworkClient(Constant.BASE_URL, supportInterceptor)

val apiModule: Module = module {
    single { supportInterceptor }
    single { bikeStationApi }
}

private val bikeStationApi: BikeStationApi = retrofit.create(BikeStationApi::class.java)