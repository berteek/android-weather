package com.berteek.weather.domain

import com.google.gson.annotations.SerializedName

data class CurrentWeatherData(
    val description: String,
    @SerializedName("temp") val temperature: Float
)