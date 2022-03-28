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
import androidx.navigation.ui.setupWithNavController
import com.jagmeet.android.hourlyweather.R
import com.jagmeet.android.hourlyweather.databinding.FragmentLookUpBinding
import com.jagmeet.android.hourlyweather.databinding.FragmetWeatherDetailBinding
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherViewModel
import com.jagmeet.android.hourlyweather.ui.weather.lookup.CityLookupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailFragment : Fragment() {
    private lateinit var binding: FragmetWeatherDetailBinding
    private val hourlyWeatherViewModel: HourlyWeatherViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmetWeatherDetailBinding.inflate(layoutInflater)
        binding.myToolbar.setupWithNavController(findNavController())
        hourlyWeatherViewModel.hourlyWeatherState.observe(viewLifecycleOwner) {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hourlyWeatherViewModel.hourlyWeatherState.observe(viewLifecycleOwner) { state ->
            binding.txtTemp.text = state.selectedWeatherData?.temp.toString()
            binding.myToolbar.title = state.cityDetail?.name

            val weatherState =  state.selectedWeatherData
            binding.txtFeelsLike.text =weatherState?.feels_like.toString()
            binding.txtDesc.text = weatherState?.weather?.get(0)?.description ?: ""
            binding.txtMain.text = weatherState?.weather?.get(0)?.main ?: ""
        }
    }

}