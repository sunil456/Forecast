package com.sunil.forecast.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(

    @Embedded(prefix = "condition_")
    val condition: Condition,

    @SerializedName("feelslike_c")
    val feelslikeC: Double, // 33

    @SerializedName("feelslike_f")
    val feelslikeF: Double, // 91.4

    @SerializedName("gust_kph")
    val gustKph: Double, // 60.1

    @SerializedName("gust_mph")
    val gustMph: Double, // 37.4

    @SerializedName("is_day")
    val isDay: Int, // 0

    @SerializedName("precip_in")
    val precipIn: Double, // 0.06

    @SerializedName("precip_mm")
    val precipMm: Double, // 1.6

    @SerializedName("temp_c")
    val tempC: Double, // 28

    @SerializedName("temp_f")
    val tempF: Double, // 82.4

    val uv: Int, // 0

    @SerializedName("vis_km")
    val visKm: Double, // 2.1

    @SerializedName("vis_miles")
    val visMiles: Double, // 1

    @SerializedName("wind_dir")
    val windDir: String, // SW

    @SerializedName("wind_kph")
    val windKph: Double, // 28.1

    @SerializedName("wind_mph")
    val windMph: Double // 17.4
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}