package com.example.earthquake

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.earthquake.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var adapter: MainAdapter
    val EXTRA_MESSAGE_LANG = "lang"
    val EXTRA_MESSAGE_LAT = "lat"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MainAdapter(MainAdapter.OnClickListener { earthquake ->
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_LANG,earthquake.lng)
                putExtra(EXTRA_MESSAGE_LAT,earthquake.lat)
            }
            startActivity(intent) })
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.earthquakelist.adapter = adapter
        viewModel.earthQuakeList.observe(this, Observer {
            adapter.setEarthquakelist(it)
        })
        viewModel.errorMessage.observe(this, Observer {



        })
        viewModel.getallEarthQuakes()
    }

}