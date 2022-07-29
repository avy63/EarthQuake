package com.example.earthquake.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.earthquake.EarthQuake
import com.example.earthquake.EarthQuakeResponse
import com.example.earthquake.network.MainRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val earthQuakeList = MutableLiveData<List<EarthQuake>>()
    val errorMessage = MutableLiveData<String>()
    fun getallEarthQuakes() {
        val call = repository.getAllEarthquakes()
        call.enqueue(object : Callback<EarthQuakeResponse> {
            override fun onResponse(call: Call<EarthQuakeResponse>, response: Response<EarthQuakeResponse>) {


                val earthQuakeResponse=response.body()
                if (earthQuakeResponse != null) {
                    earthQuakeList.postValue(earthQuakeResponse.earthquakes)
                }
                //earthQuakeList.postValue(response.body())
            }
            override fun onFailure(call: Call<EarthQuakeResponse>, t: Throwable) {


                errorMessage.postValue(t.message)
            }
        })
    }
}
