package com.jagmeet.android.hourlyweather.ui.weather.weatherdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.jagmeet.android.hourlyweather.databinding.FragmentWeatherDetailBinding
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val hourlyWeatherViewModel: HourlyWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherDetailBinding.inflate(layoutInflater)
        binding.myToolbar.setupWithNavController(findNavController())
        hourlyWeatherViewModel.hourlyWeatherState.observe(viewLifecycleOwner) {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hourlyWeatherViewModel.hourlyWeatherState.observe(viewLifecycleOwner) { state ->


            // hardcoding of °C to be changed
            binding.txtTemp.text = "${state.selectedWeatherData?.temp?.toInt()}°C"
            binding.myToolbar.title = state.cityDetail?.name
            val weatherState = state.selectedWeatherData

            weatherState?.let {
                binding.txtFeelsLike.text = "${weatherState?.feels_like?.toInt()}°C"
                binding.txtDesc.text = weatherState.weather.get(0)?.description ?: ""
                binding.txtMain.text = weatherState?.weather?.get(0)?.main ?: ""
            }
        }
    }

}