package com.jagmeet.android.hourlyweather.ui.weather

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jagmeet.android.hourlyweather.R
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
       // setSupportActionBar(binding.myToolbar)
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        val navController = navHostFragment.navController
//        binding.myToolbar.setupWithNavController(navController)
//        Log.d("jagmeetnir", "object ${viewModel}")
//        viewModel.cityLookUpState.observe(this) { state ->
//                binding.myToolbar.title = state.cityDetail?.name
//        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("test", "test")

    }

//    override fun onSupportNavigateUp(): Boolean {
//        Toast.makeText(this, "dhskdsd", Toast.LENGTH_LONG).show()
//        val navController = findNavController(R.id.fragment_container_view)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


}