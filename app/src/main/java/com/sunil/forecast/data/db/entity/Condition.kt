package com.sunil.forecast.data.db.entity


import com.google.gson.annotations.SerializedName

data class Condition(
    val code: Int, // 1183
    val icon: String, // //cdn.apixu.com/weather/64x64/night/296.png
    val text: String // Light rain
)