package com.sunil.forecast.data.provider

import com.sunil.forecast.data.db.entity.WeatherLocation

interface LocationProvider
{
    suspend fun hasLocationChanged(lastWeatherLocation : WeatherLocation) : Boolean
    suspend fun getPreferredLocationString() : String
}