package com.example.digikraftassignment.app.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.digikraftassignment.app.domain.model.DFeatures
import com.example.digikraftassignment.app.domain.usecase.BikeStationUseCase
import com.example.digikraftassignment.core.extension.setError
import com.example.digikraftassignment.core.extension.setLoading
import com.example.digikraftassignment.core.extension.setSuccess
import com.example.digikraftassignment.core.util.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val bikeStationUseCase: BikeStationUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val liveDataBikeStations = MutableLiveData<Resource<List<DFeatures>>>()

    fun getBikeStations() {
        liveDataBikeStations.setLoading()
        compositeDisposable.add(
            bikeStationUseCase.getBikeStations()
                .subscribeOn(Schedulers.io())
                .map { it }
                .subscribe({
                    liveDataBikeStations.setSuccess(it, null, null)
                },{
                    liveDataBikeStations.setError(it.message)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}