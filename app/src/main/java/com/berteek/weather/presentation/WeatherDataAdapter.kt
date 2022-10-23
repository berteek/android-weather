package com.berteek.weather.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.berteek.weather.databinding.ItemWeatherBinding
import com.berteek.weather.domain.HourlyWeatherData

class WeatherDataAdapter : RecyclerView.Adapter<WeatherDataAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<HourlyWeatherData>() {
        override fun areItemsTheSame(
            oldItem: HourlyWeatherData,
            newItem: HourlyWeatherData
        ): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(
            oldItem: HourlyWeatherData,
            newItem: HourlyWeatherData
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    var hourlyWeatherData: List<HourlyWeatherData>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount(): Int = hourlyWeatherData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val weatherData = hourlyWeatherData[position]
            time.text = weatherData.time.toString()
            description.text = weatherData.description
            temperature.text = weatherData.temperature.toString()
        }
    }
}