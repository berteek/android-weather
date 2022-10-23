package com.berteek.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    // lat = 54.2  lon = 49.6  lang = ru  units = metric  appid = 94ce2d9e62715a88d0e0ed793fd87fb9

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeatherData(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String,
        @Query("lang") language: String,
        @Query("units") units: String
    ): Response<CurrentWeatherData>

    @GET("/data/2.5/forecast")
    suspend fun getHourlyWeatherData(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
        @Query("appid") apiKey: String,
        @Query("lang") language: String,
        @Query("units") units: String
    ): Response<List<HourlyWeatherData>>
}