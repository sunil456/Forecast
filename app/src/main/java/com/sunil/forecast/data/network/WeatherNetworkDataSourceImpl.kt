package com.sunil.forecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunil.forecast.data.network.response.CurrentWeatherResponse
import com.sunil.forecast.data.network.response.FutureWeatherResponse
import com.sunil.forecast.internal.NoConnectivityException


const val FORECAST_DAYS_COUNT = 7
class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService : ApixuWeatherApiService
) : WeatherNetworkDataSource
{
    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()

    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try
        {
            val fetchCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location , languageCode)
                .await()

            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        }
        catch (e : NoConnectivityException)
        {
            Log.e("Connectivity" , "No Internet Connection." , e)
        }
    }

    private val _downloadFutureWeather = MutableLiveData<FutureWeatherResponse>()

    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadFutureWeather

    override suspend fun fetchFutureWeather(location: String,languageCode: String) {

        try {
            val fetchFutureWeather = apixuWeatherApiService.
                getFutureWeather(location,FORECAST_DAYS_COUNT ,languageCode)
                .await()
            _downloadFutureWeather.postValue(fetchFutureWeather)
        }
        catch(e : NoConnectivityException)
        {
            Log.e("Connectivity" , "No Internet Connection." , e)
        }
    }
}