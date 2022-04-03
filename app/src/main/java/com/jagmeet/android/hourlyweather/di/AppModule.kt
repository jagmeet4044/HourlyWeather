package com.jagmeet.android.hourlyweather.di

import com.jagmeet.android.hourlyweather.datasource.network.RequestInterceptor
import com.jagmeet.android.hourlyweather.datasource.network.UrlProvider
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookUpRepository
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookUpRepositoryImpl
import com.jagmeet.android.hourlyweather.datasource.network.citylookup.CityLookupService
import com.jagmeet.android.hourlyweather.datasource.network.weather.WeatherRepository
import com.jagmeet.android.hourlyweather.datasource.network.weather.WeatherRepositoryImpl
import com.jagmeet.android.hourlyweather.datasource.network.weather.WeatherService
import com.techyourchance.dagger2course.common.dependnecyinjection.RetroFitCityLookup
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        @RetrofitHourlyWeather
        fun weatherServiceApi(urlProvider: UrlProvider): Retrofit {
            return Retrofit.Builder()
                .baseUrl(urlProvider.getWeatherUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }

        private fun getOkHttpClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            client.addInterceptor(RequestInterceptor())
            return client.build()
        }

        @Provides
        @Singleton
        @RetroFitCityLookup
        fun cityLookupServiceApi(urlProvider: UrlProvider): Retrofit {
            return Retrofit.Builder()
                .baseUrl(urlProvider.getGeocodingUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        fun urlProvider() = UrlProvider()

        @Provides
        @Singleton
        fun weatherApi(@RetrofitHourlyWeather retrofit: Retrofit): WeatherService =
            retrofit.create(WeatherService::class.java)

        @Provides
        @Singleton
        fun cityLookupApi(@RetroFitCityLookup retrofit: Retrofit): CityLookupService =
            retrofit.create(CityLookupService::class.java)
    }

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    abstract fun bindCityLookUpRepository(
        cityLookUpRepositoryImpl: CityLookUpRepositoryImpl
    ): CityLookUpRepository
}