package com.jagmeet.android.hourlyweather.ui.weather.lookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jagmeet.android.hourlyweather.R
import com.jagmeet.android.hourlyweather.databinding.FragmentCityLookUpBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CityLookUpFragment : Fragment() {
    private lateinit var binding: FragmentCityLookUpBinding
    private val viewModel: CityLookupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCityLookUpBinding.inflate(layoutInflater)
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
                    .navigate(R.id.action_lookupFragment_to_weatherListFragment, Bundle().apply {
                        putParcelable("cityInfo", state.cityDetail)
                    })
            }

            state.errorMessages?.firstOrNull()?.let {
                Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT)
                    .show()
                viewModel.messageShown(it.id)
            }
        }

        binding.btnLookup.setOnClickListener {
            val city = binding.lookupEditTxt.text.toString()
            Timber.d("city $city")
            viewModel.getCityDetail(city)
        }
    }

}