package com.sunil.forecast.ui.weather.future.list


import com.sunil.forecast.data.provider.UnitProvider
import com.sunil.forecast.data.repository.ForecastRepository
import com.sunil.forecast.internal.lazyDeferred
import com.sunil.forecast.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel(
    forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository,unitProvider) {

    val weatherEntry by lazyDeferred{
        forecastRepository.getFutureWeatherList(LocalDate.now() , super.isMetricUnit)
    }
}
