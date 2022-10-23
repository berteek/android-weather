package com.berteek.weather.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.berteek.weather.data.RetrofitInstance
import com.berteek.weather.databinding.ActivityMainBinding
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var weatherDataAdapter: WeatherDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            val currentResponse = try {
                RetrofitInstance.api.getCurrentWeatherData(
                    54.2f,
                    49.6f,
                    "94ce2d9e62715a88d0e0ed793fd87fb9",
                    "ru",
                    "metric"
                )
            } catch (e: IOException) {
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            val hourlyResponse = try {
                RetrofitInstance.api.getHourlyWeatherData(
                    54.2f,
                    49.6f,
                    "94ce2d9e62715a88d0e0ed793fd87fb9",
                    "ru",
                    "metric"
                )
            } catch (e: IOException) {
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (hourlyResponse.isSuccessful &&
                hourlyResponse.body() != null &&
                currentResponse.isSuccessful &&
                currentResponse.body() != null) {
                weatherDataAdapter.hourlyWeatherData = hourlyResponse.body()!!.list.subList(0, 8)
                binding.apply {
                    currentTemperature.text = "${currentResponse.body()!!.main.temperature.toInt()}°"
                    currentWeatherDescription.text = currentResponse.body()!!.weather[0].description.replaceFirstChar { it.uppercase() }
                }
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun setupRecyclerView() {
        binding.weatherHours.apply {
            weatherDataAdapter = WeatherDataAdapter()
            adapter = weatherDataAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}