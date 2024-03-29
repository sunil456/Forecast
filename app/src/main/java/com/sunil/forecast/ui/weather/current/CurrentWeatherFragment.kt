package com.sunil.forecast.ui.weather.current

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sunil.forecast.R
import com.sunil.forecast.internal.glide.GlideApp
import com.sunil.forecast.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopedFragment() , KodeinAware {

    override val kodein by kodein()

    private val viewModelFactory : CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this , viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() =  launch {
        val currentWeather = viewModel.weather.await()
        val weatherLocation = viewModel.weatherLocation.await()

        weatherLocation.observe(this@CurrentWeatherFragment , Observer {location ->
            if (location == null) return@Observer

            updateLocation(location.name)
        })

        currentWeather.observe(this@CurrentWeatherFragment , Observer {
            if (it == null) return@Observer

            group_loading.visibility = View.GONE

            updateDateToday()
            updateTemperature(it.temperature , it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection , it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric : String , imperial : String) : String
    {
        return if(viewModel.isMetricUnit) metric else imperial
    }


    private fun updateLocation(location : String)
    {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToday()
    {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    @SuppressLint("SetTextI18n")
    private fun updateTemperature(temperature: Double, feelsLike : Double)
    {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C","°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition : String)
    {
        textView_condition.text = condition
    }

    @SuppressLint("SetTextI18n")
    private fun updatePrecipitation(precipitationVolume : Double)
    {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm","in")

        textView_precipitation.text = "Precipitation: $precipitationVolume $unitAbbreviation"
    }

    @SuppressLint("SetTextI18n")
    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }

    @SuppressLint("SetTextI18n")
    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi.")
        textView_visibility.text = "Visibility: $visibilityDistance $unitAbbreviation"
    }

}
