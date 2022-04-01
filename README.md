# <b>openWeather</b>
A simple android app to fetch Hourly weather of specified City. This app demonstrates MVVM Architecture with Unidirectional data flow.


OpenWeatherMap's geocoding api(https://openweathermap.org/api/geocoding-api) is being used to fetch city latitude and longitude "OpenWeatherMap"
OpenWeatherMap's Onecall api(https://openweathermap.org/api/one-call-api) is being used to fetch city hourly weather

<h2 align="left">Screenshots</h2>  
<h4 align="center">  
<img src="/screenshots/city_lookup.jpeg" width="30%" vspace="10" hspace="10">  
<img src="/screenshots/hourly_weather_list.jpeg" width="30%" vspace="10" hspace="10"">
<img src="/screenshots/hourly_weather_detail.jpeg" width="30%" vspace="10" hspace="10"">

#### App Architecture.
* [Unidirectional data flow (UDF)](https://developer.android.com/jetpack/compose/architecture#udf), where state flows down composables and events flow up from composables.
* A single-activity architecture, using the [Jetpack-Navigation](https://developer.android.com/guide/navigation) dependency to manage navigation between composables.
* Pattern Model-View-ViewModel (MVVM) facilitating separation of development of the graphical user interface.

#### App Specs
* Minimum SDK 26
* Target SDK 31
* Compile SDK 31

#### Libraries
* [Kotlin](https://kotlinlang.org/)
* Android Architecture Components (ViewModel, Navigation Component, ConstraintLayout)
* [Glide](https://github.com/bumptech/glide) An image loading and caching library for Android focused on smooth scrolling
* [Hilt](https://dagger.dev/hilt/) for dependency injection.
* [Retrofit](https://square.github.io/retrofit) for fetching data from the OpenWeatherMap API.
* [Timber](https://github.com/JakeWharton/timber) for logging events.
