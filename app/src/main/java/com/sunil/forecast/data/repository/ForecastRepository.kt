package com.sunil.forecast.data.repository

import androidx.lifecycle.LiveData
import com.sunil.forecast.data.db.entity.WeatherLocation
import com.sunil.forecast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import com.sunil.forecast.data.db.unitlocalized.future.detail.UnitSpecificDetailFutureWeatherEntry
import com.sunil.forecast.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import org.threeten.bp.LocalDate

interface ForecastRepository
{
    suspend fun getCurrentWeather(metric : Boolean) : LiveData<out UnitSpecificCurrentWeatherEntry>

    suspend fun getWeatherLocation() : LiveData<WeatherLocation>

    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>

    suspend fun getFutureWeatherByDate(date: LocalDate, metric: Boolean): LiveData<out UnitSpecificDetailFutureWeatherEntry>
}