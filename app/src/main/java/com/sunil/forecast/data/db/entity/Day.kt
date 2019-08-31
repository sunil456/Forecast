package com.sunil.forecast.data.db.entity


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("avgtemp_c")
    val avgtempC: Double, // 28
    @SerializedName("avgtemp_f")
    val avgtempF: Double, // 82.4
    @SerializedName("avgvis_km")
    val avgvisKm: Double, // 10
    @SerializedName("avgvis_miles")
    val avgvisMiles: Double, // 6
    @Embedded(prefix = "condition_")
    val condition: Condition,
    @SerializedName("maxtemp_c")
    val maxtempC: Double, // 28.9
    @SerializedName("maxtemp_f")
    val maxtempF: Double, // 84
    @SerializedName("maxwind_kph")
    val maxwindKph: Double, // 29.9
    @SerializedName("maxwind_mph")
    val maxwindMph: Double, // 18.6
    @SerializedName("mintemp_c")
    val mintempC: Double, // 27.3
    @SerializedName("mintemp_f")
    val mintempF: Double, // 81.1
    @SerializedName("totalprecip_in")
    val totalprecipIn: Double, // 0.16
    @SerializedName("totalprecip_mm")
    val totalprecipMm: Double, // 4.1
    val uv: Double // 12.3
)