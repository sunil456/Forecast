package com.sunil.forecast.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sunil.forecast.data.network.response.CurrentWeatherResponse
import com.sunil.forecast.data.network.response.FutureWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//dfb60e4c06534d59b88113929191707

const val API_KEY = "dfb60e4c06534d59b88113929191707"

//https://api.apixu.com/v1/current.json?key=dfb60e4c06534d59b88113929191707&q=mumbai
interface ApixuWeatherApiService
{
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location : String,
        @Query("lang") languageCode : String = "en"
    ): Deferred<CurrentWeatherResponse>

//    http://api.apixu.com/v1/forecast.json?key=dfb60e4c06534d59b88113929191707&q=Mumbai&days=1
    @GET("forecast.json")
    fun getFutureWeather(
        @Query("q") location: String,
        @Query("days") days : Int,
        @Query("lang") languageCode: String
    ) : Deferred<FutureWeatherResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ) : ApixuWeatherApiService {

            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key" , API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}