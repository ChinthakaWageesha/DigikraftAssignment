package com.example.digikraftassignment.core.util

data class Resource<out T> constructor(
    val state: ResourceState,
    val features: T? = null,
    val crs: Any? = null,
    val type: String? = null,
)
