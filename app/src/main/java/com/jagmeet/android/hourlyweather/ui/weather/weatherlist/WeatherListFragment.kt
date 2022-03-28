package com.jagmeet.android.hourlyweather.ui.weather.weatherlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jagmeet.android.hourlyweather.R
import com.jagmeet.android.hourlyweather.databinding.FragmentWeatherListBinding
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherAdapter
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherViewModel
import com.jagmeet.android.hourlyweather.ui.weather.Interaction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListFragment : Fragment() {
    private lateinit var adapter: HourlyWeatherAdapter
    private lateinit var binding: FragmentWeatherListBinding
    private val viewModel: HourlyWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState==null)
        viewModel.getHourlyWeather()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        // Log.d("jagmeetnir", "object ${viewModel}")
        //  Log.d("jagmeetnir"," "+ arguments?.getDouble("lat"))
        //  arguments?.getString("city")?.let { getWeather(it) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.myToolbar.setupWithNavController(findNavController())

        viewModel.hourlyWeatherData.observe(viewLifecycleOwner) {
            adapter.submitList(it.hourly)
        }
        viewModel.cityLookUpState.observe(viewLifecycleOwner) {
            binding.myToolbar.title = it.cityDetail?.name
        }
    }

    private fun initRecyclerView() {
        adapter = HourlyWeatherAdapter(object : Interaction {
            override fun onItemSelected(position: Int, item: HourlyData) {
                viewModel.setSelectedData(item)
                findNavController().navigate(R.id.action_weatherListFragment_to_weatherDetailFragment)
            }
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

}