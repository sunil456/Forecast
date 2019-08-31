package com.sunil.forecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.sunil.forecast.data.provider.UnitProvider
import com.sunil.forecast.data.repository.ForecastRepository
import com.sunil.forecast.internal.UnitSystem
import com.sunil.forecast.internal.lazyDeferred
import com.sunil.forecast.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider)
{
    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }

}
