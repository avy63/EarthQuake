package com.example.earthquake
data class EarthQuakeResponse(val earthquakes:List<EarthQuake>)
public data class EarthQuake(val datetime: String, val depth: Float, val lng: Float, val src: String,
                      val eqid: String,val magnitude: Float, val lat: Float)
