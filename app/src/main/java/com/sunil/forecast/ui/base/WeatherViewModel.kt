package com.sunil.forecast.ui.base

import androidx.lifecycle.ViewModel
import com.sunil.forecast.data.provider.UnitProvider
import com.sunil.forecast.data.repository.ForecastRepository
import com.sunil.forecast.internal.UnitSystem
import com.sunil.forecast.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
): ViewModel()
{
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit : Boolean
        get() = unitSystem == UnitSystem.METRIC


    val weatherLocation by lazyDeferred{
        forecastRepository.getWeatherLocation()
    }
}
