package com.example.earthquake.network

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllEarthquakes() = retrofitService.getAllEarthQuakes()
}
