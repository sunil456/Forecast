package com.sunil.forecast

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sunil.forecast.data.db.ForecastDatabase
import com.sunil.forecast.data.network.*
import com.sunil.forecast.data.provider.LocationProvider
import com.sunil.forecast.data.provider.LocationProviderImpl
import com.sunil.forecast.data.provider.UnitProvider
import com.sunil.forecast.data.provider.UnitProviderImpl
import com.sunil.forecast.data.repository.ForecastRepository
import com.sunil.forecast.data.repository.ForecastRepositoryImpl
import com.sunil.forecast.ui.weather.current.CurrentWeatherViewModelFactory
import com.sunil.forecast.ui.weather.future.detail.FutureDetailWeatherViewModelFactory
import com.sunil.forecast.ui.weather.future.list.FutureWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

class ForecastApplication : Application() , KodeinAware
{
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }

        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }

        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }

        bind() from singleton { instance<ForecastDatabase>().futureWeatherDao() }

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }

        bind() from singleton { ApixuWeatherApiService(instance()) }

        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }

        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }

        bind<LocationProvider>() with singleton { LocationProviderImpl(instance() , instance()) }

        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance() , instance() , instance() , instance() , instance()) }

        bind<UnitProvider>() with  singleton { UnitProviderImpl(instance()) }

        bind() from provider { CurrentWeatherViewModelFactory(instance() , instance()) }

        bind() from provider { FutureWeatherViewModelFactory(instance() , instance()) }

        bind() from factory { detailDate : LocalDate -> FutureDetailWeatherViewModelFactory(detailDate , instance() , instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this , R.xml.preferences , false)
    }
}