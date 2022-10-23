package com.berteek.weather.domain

import com.google.gson.annotations.SerializedName

data class HourlyWeatherData(
    @SerializedName("dt") val time: Long,
    val weather: List<WeatherWeatherData>,
    @SerializedName("main") val mainWeatherData: MainWeatherData
)

data class HourlyWeatherDataList(
    @SerializedName("list") val list: List<HourlyWeatherData>
)

data class MainWeatherData(
    @SerializedName("temp") val temperature: Float
)

data class WeatherWeatherData(
    val description: String
)
