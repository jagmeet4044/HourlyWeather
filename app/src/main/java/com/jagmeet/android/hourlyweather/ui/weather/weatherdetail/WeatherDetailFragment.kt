package com.jagmeet.android.hourlyweather.ui.weather.weatherdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jagmeet.android.hourlyweather.R
import com.jagmeet.android.hourlyweather.databinding.FragmentLookUpBinding
import com.jagmeet.android.hourlyweather.databinding.FragmetWeatherDetailBinding
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmetWeatherDetailBinding
    private val viewModel: HourlyWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmetWeatherDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}