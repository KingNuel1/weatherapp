package com.example.weatherapp.models

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "icon") val icon: String,
    @Json(name = "description") val description: String,
)

data class CurrentConditionsData(
    @Json(name = "temp") val temp: Float,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
)

data class CurrentConditions(
    @Json(name = "name") val location: String,
    @Json(name = "weather") val weatherSummary: List<WeatherData>,
    @Json(name = "main") val conditions: CurrentConditionsData,
){
    val weatherIconUrl : String
        get() = "https://openweathermap.org/img/wn/${weatherSummary.firstOrNull()?.icon}@2x.png"
    val weatherDescription : String?
        get() = weatherSummary.firstOrNull()?.description
    val currentTemp : Float
        get() = conditions.temp
    val feelsLike : Float
        get() = conditions.feelsLike
    val pressure : Int
        get() = conditions.pressure
    val humidity : Int
        get() = conditions.humidity
    val maxTemp : Float
        get() = conditions.tempMax
    val minTemp : Float
        get() = conditions.tempMin
}


