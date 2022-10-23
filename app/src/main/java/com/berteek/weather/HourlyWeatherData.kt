package com.berteek.weather

import android.icu.text.SimpleDateFormat
import com.google.gson.annotations.SerializedName

data class HourlyWeatherData(
    @SerializedName("dt") val time: SimpleDateFormat,
    val description: String,
    @SerializedName("temp") val temperature: Float
)
