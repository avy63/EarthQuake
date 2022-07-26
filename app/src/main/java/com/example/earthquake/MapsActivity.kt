package com.example.earthquake

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    val EXTRA_MESSAGE_LANG = "lang"
    val EXTRA_MESSAGE_LAT = "lat"
    var latitude:Double =0.0
    var longitude: Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        longitude = intent.getDoubleExtra(EXTRA_MESSAGE_LANG,0.0)
        latitude= intent.getDoubleExtra(EXTRA_MESSAGE_LAT,0.0)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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