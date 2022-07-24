package com.example.earthquake

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllEarthquakes() = retrofitService.getAllEarthQuakes()
}
