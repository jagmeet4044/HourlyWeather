package com.jagmeet.android.hourlyweather.ui.weather.weatherlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jagmeet.android.hourlyweather.databinding.WeatherDataItemBinding
import com.jagmeet.android.hourlyweather.model.HourlyData
import com.jagmeet.android.hourlyweather.model.HourlyWeatherData

class HourlyWeatherAdapter(private val interaction: Interaction? = null) :
    ListAdapter<HourlyData, RecyclerView.ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherViewHolder.from(parent, interaction)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WeatherViewHolder -> holder.bind(getItem(position))
        }
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<HourlyData>() {
    override fun areItemsTheSame(oldItem: HourlyData, newItem: HourlyData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HourlyData, newItem: HourlyData): Boolean {
        return oldItem == newItem
    }

}


class WeatherViewHolder(
    private val binding: WeatherDataItemBinding,
    private val interaction: Interaction?
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hourlyData: HourlyData) {
        // hardcoding of °C to be changed
        binding.txtMain.text = "${hourlyData.temp.toInt()}°C"
        binding.txtDescription.text = hourlyData.weather[0].main
        binding.txtTime.text = hourlyData.dt
        Glide.with(binding.root.context)
            .load("https://openweathermap.org/img/wn/${hourlyData.weather[0].icon}@2x.png")
            .into(binding.imageView)
        itemView.setOnClickListener {
            interaction?.onItemSelected(adapterPosition, hourlyData)
        }
    }

    companion object {
        fun from(parent: ViewGroup, interaction: Interaction?): WeatherViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            var binding = WeatherDataItemBinding.inflate(layoutInflater, parent, false)
            return WeatherViewHolder(binding, interaction)
        }
    }
}

interface Interaction {
    fun onItemSelected(position: Int, item: HourlyData)
}