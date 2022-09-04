package com.example.digikraftassignment.app.dependencyinjection

import com.example.digikraftassignment.app.data.datasource.BikeStationDataSource
import com.example.digikraftassignment.app.data.repository.BikeStationRepositoryImpl
import com.example.digikraftassignment.app.domain.repository.BikeStationRepository
import com.example.digikraftassignment.app.domain.usecase.BikeStationUseCase
import com.example.digikraftassignment.app.remotedatasource.BikeStationRemoteDataSourceImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        listOf(
            viewModelModule,
            useCaseModule,
            repositoryModule,
            dataSourceModule
        )
    )
}

val viewModelModule: Module = module {

}

val useCaseModule: Module = module(override = true) {
    factory { BikeStationUseCase(bikeStationRepository = get()) }
}

val repositoryModule: Module = module(override = true) {
    single {
        BikeStationRepositoryImpl(
            bikeStationDataSource = get()
        ) as BikeStationRepository
    }
}

val dataSourceModule: Module = module(override = true) {
    single {
        BikeStationRemoteDataSourceImpl(
            bikeStationApi = get()
        ) as BikeStationDataSource
    }
}