package com.sunil.forecast.data.network.response


import com.google.gson.annotations.SerializedName
import com.sunil.forecast.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)