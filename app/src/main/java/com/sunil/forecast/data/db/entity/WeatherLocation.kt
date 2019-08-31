package com.sunil.forecast.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

const val WEATHER_LOCATION_ID = 0
@Entity(tableName = "weather_location")
data class WeatherLocation(
    val country: String, // India
    val lat: Double, // 18.98
    val localtime: String, // 2019-08-10 21:15

    @SerializedName("localtime_epoch")
    val localtimeEpoch: Long, // 1565451929
    val lon: Double, // 72.83
    val name: String, // Mumbai
    val region: String, // Maharashtra

    @SerializedName("tz_id")
    val tzId: String // Asia/Kolkata
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = WEATHER_LOCATION_ID

    val zonedDateTime : ZonedDateTime
        get() {
            val instant = Instant.ofEpochSecond(localtimeEpoch)
            val zoneId = ZoneId.of(tzId)

            return ZonedDateTime.ofInstant(instant , zoneId)
        }
}