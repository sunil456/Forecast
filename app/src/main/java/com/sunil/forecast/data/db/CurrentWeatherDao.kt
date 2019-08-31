package com.sunil.forecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunil.forecast.data.db.entity.CURRENT_WEATHER_ID
import com.sunil.forecast.data.db.entity.CurrentWeatherEntry
import com.sunil.forecast.data.db.unitlocalized.current.ImperialCurrentWeatherEntry
import com.sunil.forecast.data.db.unitlocalized.current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry : CurrentWeatherEntry)


    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric() : LiveData<MetricCurrentWeatherEntry>

    @Query("SELECT * FROM current_weather WHERE id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial() : LiveData<ImperialCurrentWeatherEntry>
}