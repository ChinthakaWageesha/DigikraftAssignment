package com.example.digikraftassignment.app.domain.usecase

import com.example.digikraftassignment.app.domain.repository.BikeStationRepository

class BikeStationUseCase(private val bikeStationRepository: BikeStationRepository) {

    fun getBikeStations() = bikeStationRepository.getBikeStations()

}