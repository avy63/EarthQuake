package com.example.earthquake.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.earthquake.*
import com.example.earthquake.databinding.ActivityMainBinding
import com.example.earthquake.network.MainRepository
import com.example.earthquake.network.RetrofitService
import com.example.earthquake.viewmodel.LiveDataInternetConnections
import com.example.earthquake.viewmodel.MainViewModel
import com.example.earthquake.viewmodel.MyViewModelFactory
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var adapter: MainAdapter
    private lateinit var loadingDialog: LoadingDialog

    val EXTRA_MESSAGE="key"
    var isCalledAPI: Boolean=false
    private lateinit var cld : LiveDataInternetConnections
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog= LoadingDialog(this,layoutInflater)
        adapter = MainAdapter(MainAdapter.OnClickListener { earthquake ->
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE,earthquake)
            }
            startActivity(intent) })
        cld = LiveDataInternetConnections(application)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)
        binding.earthquakelist.adapter = adapter
        viewModel.earthQuakeList.observe(this, Observer {
            loadingDialog.hideAlertDialog()
            adapter.setEarthquakelist(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            loadingDialog.hideAlertDialog()
            Snackbar.make(binding.root, "Something went wrong!!!", Snackbar.LENGTH_SHORT).show()

        })
        // checking internet connection
        cld.observe(this, { isConnected ->

            if (isConnected && !isCalledAPI) {
                isCalledAPI=true
                viewModel.getallEarthQuakes()
                loadingDialog.showAlertDialog()
            }else if (isConnected==false) {
                Snackbar.make(binding.root, "No internt Connection", Snackbar.LENGTH_SHORT).show()
            }
        })


    }

}