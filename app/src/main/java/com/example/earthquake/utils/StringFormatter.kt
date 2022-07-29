package com.example.earthquake.utils

import android.util.Log
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun stringValueofdepth(depth:Double): String {
    return "Depth: "+depth
}
fun stringValueofMagnitude(meg:Double):String{
    return String.format("%.2f", meg)
}

fun stringValuedate(date:String):String{
    val splitStr: List<String> = date.split(" ")
    val originalFormat: DateFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
    val targetFormat: DateFormat = SimpleDateFormat("MMMM dd, yyyy")
    val date: Date = originalFormat.parse(splitStr[0])
    val formattedDate: String = targetFormat.format(date) // 20120821

    return "Time: "+formattedDate +" "+splitStr[1]
}
fun stringValueSource(source:String):String{
    return "Source: "+ source.uppercase()
}
