package com.example.earthquake

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject

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
