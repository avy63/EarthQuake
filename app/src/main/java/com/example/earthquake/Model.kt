package com.example.earthquake
data class EarthQuakeResponse(val earthquakes:List<EarthQuake>)
public data class EarthQuake(val datetime: String, val depth: Double, val lng: Double, val src: String,
                      val eqid: String,val magnitude: Double, val lat: Double)
