package com.example.digikraftassignment.core.extension

import androidx.lifecycle.MutableLiveData
import com.example.digikraftassignment.core.util.Resource
import com.example.digikraftassignment.core.util.ResourceState

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T, crs: Any?, message: String?) =
    postValue(Resource(ResourceState.SUCCESS, data, crs, message))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(
        Resource(
            ResourceState.LOADING,
            value?.features
        )
    )

fun <T> MutableLiveData<Resource<T>>.setError(message: String?) =
    postValue(
        Resource(
            ResourceState.ERROR,
            value?.features,
            null,
            message
        )
    )