package com.earl.productivityinside.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.lifecycleScope
import com.earl.productivityinside.App
import com.earl.productivityinside.R
import com.earl.productivityinside.databinding.FragmentMainBinding
import com.earl.productivityinside.presentation.LocaleHelper.setLocale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainFragment : BaseFragment<FragmentMainBinding>(), UpdateTimeListener {

    @Inject lateinit var viewModel: MainFragmentViewModel
    private var currentServer = SERVER_ONE

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.usaLang.setOnClickListener { changeLanguage(ENGLISH) }
        binding.ruLang.setOnClickListener { changeLanguage(RUSSIAN) }
        binding.updateBtn.setOnClickListener { update() }
    }

    private fun initViews() {
        navigator.showProgressBar()
        initSpinner(RUSSIAN)
        fetchLocation(RUSSIAN)
        fetchWeather(RUSSIAN)
        navigator.hideProgressBar()
    }

    private fun initSpinner(lang: String) {
        val spinner = binding.serverSpinner
        val context = setLocale(requireContext(), lang)
        spinner.adapter = ArrayAdapter.createFromResource(context, R.array.servers_name_spinner, R.layout.spinner_item)
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (parent?.selectedItem) {
                    context.resources.getString(R.string.serverOne) -> {
                        currentServer = SERVER_ONE
                        update()
                    }
                    context.resources.getString(R.string.serverTwo) ->  {
                        currentServer = SERVER_TWO
                        update()
                    }
                    context.resources.getString(R.string.serverThree) -> {
                        currentServer = SERVER_THREE
                        update()
                    }
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun changeLanguage(lang: String) {
        navigator.showProgressBar()
        val context = setLocale(requireContext(), lang)
        val resources = context.resources
        with(binding){
            fetchLocation(lang)
            fetchWeather(lang)
            initSpinner(lang)
            lastUpdateTime.text = resources.getString(R.string.last_update_time)
            placeInfo.text = resources.getString(R.string.place_info)
            weather.text = resources.getString(R.string.weather)
        }
        navigator.hideProgressBar()
    }

    private fun fetchLocation(lang: String) {
        viewModel.fetchLocationByIp(lang, this)
        lifecycleScope.launchWhenStarted {
            viewModel._locationInfo.onEach { location ->
                if (location != null) {
                    val context = setLocale(requireContext(), lang)
                    when(currentServer) {
                        SERVER_ONE -> viewModel.fetchWeatherFromServerOne(location.provideCoordinates())
                        SERVER_TWO -> viewModel.fetchWeatherFromServerTwo(location.provideCoordinates())
                        SERVER_THREE -> viewModel.fetchWeatherFromServerThree(location.provideCoordinates())
                    }
                    location.provideLocationInfo(
                        context,
                        binding.continent,
                        binding.county,
                        binding.region,
                        binding.cityTitle
                    )
                }
            }.collect()
        }
    }

    private fun fetchWeather(lang: String) {
        lifecycleScope.launchWhenStarted {
            viewModel._weatherInfo.onEach { weather ->
                if (weather != null) {
                    val context = setLocale(requireContext(), lang)
                    weather.provideWeatherInfo(
                        context,
                        binding.temperature,
                        binding.pressure,
                        binding.weatherImage
                    )
                    navigator.hideProgressBar()
                }
            }.collect()
        }
    }

    private fun update() {
        navigator.showProgressBar()
        val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
        fetchLocation(currentLocale.toString())
        fetchWeather(currentLocale.toString())
        navigator.hideProgressBar()
    }

    override fun saveLastUpdateTime(date: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]
            val context = setLocale(requireContext(), currentLocale.toString())
            val dateGiver = DateTimeGiver()
            val dayOfMonthFormatter = dateGiver.fetchDayOfMonthFormat()
            binding.lastUpdateTime.text = context.resources.getString(
                R.string.last_update_time,
                date.format(dayOfMonthFormatter)
            )
        }
    }

    companion object {

        fun newInstance() = MainFragment()
        private const val RUSSIAN = "ru"
        private const val ENGLISH = "en"
        private const val SERVER_ONE = "SERVER_ONE"
        private const val SERVER_TWO = "SERVER_TWO"
        private const val SERVER_THREE = "SERVER_THREE"
    }
}