package com.example.earthquake

import android.util.Log

fun stringValueofdepth(depth:Double): String {
    return "Depth: "+depth
}
fun stringValueofMagnitude(meg:Double):String{
    return String.format("%.2f", meg)
}

fun stringValuedate(date:String):String{
    return "Time: "+date
}
fun stringValueSource(source:String):String{
    return "Source: "+ source.uppercase()
}
fun getcolor(magnitude:Double): Int {
    Log.d("wahid", magnitude.toString())
    if (magnitude>=6.0){
        return R.color.red
    }
    else{
        return R.color.red
    }
}