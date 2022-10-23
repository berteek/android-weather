package com.berteek.weather.domain

import com.google.gson.annotations.SerializedName
import java.util.*

data class HourlyWeatherData(
    @SerializedName("dt") val time: Date,
    val description: String,
    @SerializedName("temp") val temperature: Float
)
