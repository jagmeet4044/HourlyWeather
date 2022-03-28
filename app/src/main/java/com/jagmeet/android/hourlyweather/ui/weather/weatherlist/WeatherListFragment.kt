package com.jagmeet.android.hourlyweather.ui.weather.weatherlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jagmeet.android.hourlyweather.R
import com.jagmeet.android.hourlyweather.databinding.FragmentWeatherListBinding
import com.jagmeet.android.hourlyweather.model.CityDetail
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherAdapter
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherViewModel
import com.jagmeet.android.hourlyweather.ui.weather.Interaction
import com.jagmeet.android.hourlyweather.ui.weather.lookup.CityLookupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListFragment : Fragment() {
    private lateinit var adapter: HourlyWeatherAdapter
    private lateinit var binding: FragmentWeatherListBinding
    private val hourlyWeatherViewModel: HourlyWeatherViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var cityDetail = arguments?.getParcelable<CityDetail>("cityInfo")
        if (savedInstanceState == null)
            cityDetail?.let {
                hourlyWeatherViewModel.getHourlyWeather(cityDetail)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.myToolbar.setupWithNavController(findNavController())
        hourlyWeatherViewModel.hourlyWeatherState.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.hourlyWeatherDataList)
            binding.myToolbar.title = state.cityDetail?.name

            when (state.isLoading) {
                true -> binding.progressBar.visibility = VISIBLE
                false -> binding.progressBar.visibility = GONE
            }

            state.errorMessages?.firstOrNull()?.let {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                hourlyWeatherViewModel.messageShown(it.id)
            }

        }
    }

    private fun initRecyclerView() {
        adapter = HourlyWeatherAdapter(object : Interaction {
            override fun onItemSelected(position: Int, item: HourlyData) {
                hourlyWeatherViewModel.setWeatherData(item)
                findNavController().navigate(R.id.action_weatherListFragment_to_weatherDetailFragment)
            }
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

}