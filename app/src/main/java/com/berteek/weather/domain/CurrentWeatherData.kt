package com.berteek.weather.domain

import com.google.gson.annotations.SerializedName

data class CurrentWeatherData(
    val weather: List<WeatherWeatherData>,
    val main: MainWeatherData
)