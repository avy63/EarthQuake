package com.example.earthquake

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.earthquake.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var adapter: MainAdapter
    private lateinit var loadingDialog: LoadingDialog
    val EXTRA_MESSAGE_LANG = "lang"
    val EXTRA_MESSAGE_LAT = "lat"
    var isCalledAPI: Boolean=false
    private lateinit var cld : LiveDataInternetConnections
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog=LoadingDialog(this,layoutInflater)
        adapter = MainAdapter(MainAdapter.OnClickListener { earthquake ->
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE_LANG,earthquake.lng)
                putExtra(EXTRA_MESSAGE_LAT,earthquake.lat)
            }
            startActivity(intent) })
        cld = LiveDataInternetConnections(application)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        binding.earthquakelist.adapter = adapter
        viewModel.earthQuakeList.observe(this, Observer {
            loadingDialog.hideAlertDialog()
            adapter.setEarthquakelist(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            loadingDialog.hideAlertDialog()
            Snackbar.make(binding.root, "Something went wrong!!!", Snackbar.LENGTH_SHORT).show()

        })
        cld.observe(this, { isConnected ->
            if (isConnected && !isCalledAPI) {
                isCalledAPI=true
                viewModel.getallEarthQuakes()
                loadingDialog.showAlertDialog()
            }else if(!isConnected) {
                Snackbar.make(binding.root, "No internt Connection", Snackbar.LENGTH_SHORT).show()
            }
        })


    }

}