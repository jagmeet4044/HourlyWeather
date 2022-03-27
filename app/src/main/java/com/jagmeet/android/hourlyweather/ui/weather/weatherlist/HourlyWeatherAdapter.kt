package com.jagmeet.android.hourlyweather.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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


class WeatherViewHolder(private val binding: WeatherDataItemBinding, private val interaction: Interaction?) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(hourlyData: HourlyData) {
        binding.txtMain.text = hourlyData.weather[0].main
        binding.txtDescription.text = hourlyData.temp.toString()
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