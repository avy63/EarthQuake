package com.example.earthquake.views

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.earthquake.EarthQuake
import com.example.earthquake.R
import com.example.earthquake.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.earthquake.utils.*
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    lateinit var binding: ActivityMapsBinding
    lateinit var earthquake:EarthQuake
    var latitude:Double =0.0
    var longitude: Double=0.0

    val EXTRA_MESSAGE="key"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_maps)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        earthquake= intent.getSerializableExtra(EXTRA_MESSAGE) as EarthQuake
        longitude = earthquake.lng
        latitude= earthquake.lat
        updateUI()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        setContentView(binding.root)
    }

    private fun updateUI() {
        binding.tvDepth.text= stringValueofdepth(earthquake.depth)
        binding.tvLocation.text= stringValueSource(earthquake.src)
        binding.tvTime.text= stringValuedate(earthquake.datetime)
        binding.tvMagnitude.text= stringValueofMagnitude(earthquake.magnitude)
        if (earthquake.magnitude>=6.0){
            binding.tvMagnitude.setTextColor(Color.parseColor("#ff0000"))
        }else if (earthquake.magnitude<6.0 && earthquake.magnitude>=5.0){
            binding.tvMagnitude.setTextColor(Color.parseColor("#00008B"))

        }else{
            binding.tvMagnitude.setTextColor(Color.parseColor("#ff03dac5"))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val location = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(location).title("Here is the location"))
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(5f).build()
        //Zoom in and animate the camera.
        //Zoom in and animate the camera.
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location.ce))

    }
}