package com.jagmeet.android.hourlyweather.ui.weather

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jagmeet.android.hourlyweather.databinding.ActivityHourlyWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HourlyWeatherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHourlyWeatherBinding
    private val viewModel: HourlyWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHourlyWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("jagmeetnir","object ${viewModel}")
        viewModel.hourlyWeatherData.observe(this,{})
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("test","test")

    }
}