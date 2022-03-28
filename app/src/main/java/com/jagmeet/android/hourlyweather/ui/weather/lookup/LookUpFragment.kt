package com.jagmeet.android.hourlyweather.ui.weather.lookup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jagmeet.android.hourlyweather.R
import com.jagmeet.android.hourlyweather.databinding.FragmentLookUpBinding
import com.jagmeet.android.hourlyweather.ui.weather.HourlyWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LookUpFragment : Fragment() {
    private lateinit var binding: FragmentLookUpBinding
    private val viewModel: CityLookupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLookUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.cityLookUpState.observe(viewLifecycleOwner) { state ->
            if (state.isLookUpSuccess) {
                viewModel.navigateFromLookUp()
                var b: Bundle = Bundle()
                b.putParcelable("cityInfo", state.cityDetail)
                findNavController()
                    .navigate(R.id.action_lookupFragment_to_weatherListFragment, b)
            }

            state.errorMessages?.firstOrNull()?.let {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                viewModel.messageShown(it.id)
            }
            binding.lookupEditTxt.setText("london")
        }

        binding.btnLookup.setOnClickListener {
            val city = binding.lookupEditTxt.text.toString()
            Log.d("jagmeetnir", " city " + city)
            viewModel.getCityDetail(city)
        }
    }

}