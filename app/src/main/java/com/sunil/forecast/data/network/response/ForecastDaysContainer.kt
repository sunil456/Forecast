package com.sunil.forecast.data.network.response


import com.google.gson.annotations.SerializedName
import com.sunil.forecast.data.db.entity.FutureWeatherEntry

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)