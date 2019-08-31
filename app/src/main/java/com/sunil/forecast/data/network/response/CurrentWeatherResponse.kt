package com.sunil.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import com.sunil.forecast.data.db.entity.CurrentWeatherEntry
import com.sunil.forecast.data.db.entity.WeatherLocation


data class CurrentWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)