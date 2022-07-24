package com.example.earthquake

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquake.databinding.EarthquakeViewholderBinding
import timber.log.Timber

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var earthquakes = mutableListOf<EarthQuake>()
    fun setEarthquakelist(earthquakes: List<EarthQuake>) {
        Log.d("wahid","My lis is here "+earthquakes.size)
        this.earthquakes = earthquakes.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EarthquakeViewholderBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(earthquakes[position])
    }

    override fun getItemCount(): Int {
        return earthquakes.size
    }

}
class MainViewHolder(val binding: EarthquakeViewholderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(earthQuake: EarthQuake) {
        Log.d("wahid",earthQuake.datetime+" "+earthQuake.toString())
        binding.earthquakedata=earthQuake
        binding.executePendingBindings()
    }
}